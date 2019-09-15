package com.javahack.smehelper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.joda.time.DateTime;

import java.io.*; //its very bad, but I dont have much time to rewrite this import((
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Document {
    private final String dateTime;
    private final String zakaz;
    private final String z_ogrnip;
    private final String podr;

    private String tech_oborud = "";

    private static final String ORIGINAL_PATH = "/home/talgat/prog/SmeHelperJavaHack/smehelper/src/main/resources/Dogovor.docx";
    private static final File ORIGINAL = new File(ORIGINAL_PATH);
    private static final String COPY_PATH = "/home/talgat/prog/SmeHelperJavaHack/smehelper/src/main/resources/copy.docx";
    private static final String OUTPUT_PATH = "/home/talgat/prog/SmeHelperJavaHack/smehelper/src/main/resources/output.docx";

    public Document(String zakaz, String podr) {
        this.dateTime = DateTime.now().toString().substring(0, DateTime.now().toString().indexOf("T"));
        this.z_ogrnip = "6449013711";
        this.zakaz = zakaz;
        this.podr = podr;
    }

    public Document(String dateTime, String zakaz, String z_ogrnip, String podr) {
        this.dateTime = dateTime;
        this.zakaz = zakaz;
        this.z_ogrnip = z_ogrnip;
        this.podr = podr;
    }

    public Document(String dateTime, String zakaz, String z_ogrnip, String podr, String tech_oborud) {
        this.dateTime = dateTime;
        this.zakaz = zakaz;
        this.z_ogrnip = z_ogrnip;
        this.podr = podr;
        this.tech_oborud = tech_oborud;
    }

    public byte[] getDocument() {
        try {
            Path path = copyFile();
            byte[] byteData = Files.readAllBytes(path);
//            XWPFDocument.openPackage(path.toString())

//            ClassLoader classloader = org.apache.poi.poifs.filesystem.POIFSFileSystem.class.getClassLoader();
//            URL res = classloader.getResource("org/apache/poi/util/POILogger.class");
//            String p = res.getPath();
//            System.out.println("POI came from " + p);
            System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.NullLogger");
            XWPFDocument doc = new XWPFDocument(new FileInputStream(path.toAbsolutePath().toString())/* XWPFDocument.openPackage(path.toString())*/);

            int numberToPrint = 0;

            for (XWPFParagraph para : doc.getParagraphs()) {
                List<XWPFRun> runs = para.getRuns();
                numberToPrint++;

                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null)
                        run.setText(replaceInDocument(text), 0);
                }
            }
            FileOutputStream fos = new FileOutputStream(new File(OUTPUT_PATH));
            doc.write(fos);

            return Files.readAllBytes(new File(OUTPUT_PATH).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private Path copyFile() throws IOException {
        Path copied = Paths.get(COPY_PATH);
        Path originalPath = ORIGINAL.toPath();
        Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        return copied;
    }

    private String replaceInDocument(String inp) {
        String res = inp.replace("#date_time#", dateTime);
        res = res.replace("#zakaz#", zakaz);
        res = res.replace("#z_ogrnip#", z_ogrnip);
        res = res.replace("#podr#", podr);
        res = res.replace("#tech_oborud", tech_oborud);
        return res;
    }
}
