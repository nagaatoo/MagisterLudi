package ru.MagisterLudi.webserver.VIew;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.MagisterLudi.webserver.Model.BaseDocument;
import ru.MagisterLudi.webserver.Service.SendService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@SpringUI(path = "/upload")
@Theme("valo")
public class MainForm extends UI {

    private final int MAX_LENGHT_FIELD = 15;
    private Button findButton;
    private Grid<BaseDocument> gridFile;
    private UploadReceaver uploadReceaver = new UploadReceaver();

    private SendService sendService;

    @Autowired
    public MainForm(SendService sendService) {
        this.sendService = sendService;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout outLayout =new VerticalLayout();
        TabSheet tabSheet = new TabSheet();
        tabSheet.addTab(getUploadLayout(), "Загрузка");
        tabSheet.addTab(getDownloadLayout(), "Найти файл");
        outLayout.addComponent(tabSheet);
        this.setContent(outLayout);
    }

    private VerticalLayout getDownloadLayout() {
        VerticalLayout vl =new VerticalLayout();
        HorizontalLayout hl = new HorizontalLayout();
        TextField fileName = getTextField("Имя файла");
        TextField ownerName = getTextField("Владелец");
        findButton = new Button("Найти");
        settingGridFile();

        hl.addComponent(fileName);
        hl.addComponent(ownerName);
        hl.addComponent(findButton);

        vl.addComponent(hl);
        vl.addComponent(gridFile);
        return vl;
    }

    private VerticalLayout getUploadLayout() {
        VerticalLayout vl = new VerticalLayout();

        TextField fileName = getTextField("Имя файла");
        TextField description = getTextField("Описание", 2000);
        TextField ownerName = getTextField("Владелец");
        Upload upload = new Upload("upload there", uploadReceaver);
        upload.addSucceededListener(uploadReceaver);
        upload.setImmediateMode(false);

        vl.addComponent(fileName);
        vl.addComponent(description);
        vl.addComponent(ownerName);
        vl.addComponent(upload);

        return vl;
    }

    private TextField getTextField(String name) {
        return getTextField(name, MAX_LENGHT_FIELD);
    }

    private TextField getTextField(String name, int lenght) {
        TextField field = new TextField();
        field.setCaption(name);
        field.setMaxLength(lenght);

        return field;
    }

    private void settingGridFile() {
        gridFile = new Grid();
        BaseDocument document = new BaseDocument();
        document.setUUID("123");
        document.setFileName("someName");
        document.setOwnerName("soneOwnerName");
        document.setDescription("bla-bla-bla");
        gridFile.setItems(document);
        gridFile.addColumn(item -> item.getUUID()).setCaption("uuid").setHidden(true);
        gridFile.addColumn(item -> item.getFileName()).setCaption("Имя файла");
        gridFile.addColumn(item -> item.getOwnerName()).setCaption("Имя файла");
        gridFile.addColumn(item -> item.getDescription()).setCaption("Описание");

        gridFile.addItemClickListener(item -> {
            System.out.println(item.getItem().getUUID());
        });
    }

    private void makeSaveDocumentRequest(File file) {
        BaseDocument baseDocument = new BaseDocument();
        baseDocument.setUUID("000");
        sendService.saveDocument(baseDocument);
    }

    class UploadReceaver implements Upload.Receiver, Upload.SucceededListener {

        // copypast from https://vaadin.com/docs/v8/framework/components/components-upload.html
        private static final long serialVersionUID = 2215337036540966711L;
        private OutputStream outputFile = null;
        private File file;

        @Override
        public OutputStream receiveUpload(String strFilename, String strMIMEType) {
            file = null;
            try {
                file = new File(strFilename);
                if (!file.exists()) {
                    file.createNewFile();
                }
                outputFile = new FileOutputStream(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return outputFile;
        }

        protected void finalize() {
            try {
                super.finalize();
                if (outputFile != null) {
                    outputFile.close();
                }
            } catch (Throwable exception) {
                exception.printStackTrace();
            }
        }

        @Override
        public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
            makeSaveDocumentRequest(file);
        }
    }
}
