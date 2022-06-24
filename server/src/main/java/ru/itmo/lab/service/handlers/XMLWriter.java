package ru.itmo.lab.service.handlers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import ru.itmo.lab.entity.Dragon;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class XMLWriter {
    public static void writeToXML(List<Dragon> dragons) throws IOException {
        String file = System.getenv("DATA_FILEPATH");
        XStream xStream = new XStream();
        xStream.processAnnotations(Dragon.class);
        xStream.addPermission(AnyTypePermission.ANY);

        String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" + xStream.toXML(dragons);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.write(xmlData);
        printWriter.close();
    }
}
