package ru.itmo.lab.service.handlers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.security.AnyTypePermission;
import ru.itmo.lab.entity.Dragon;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.service.OutputMessage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class XMLReader {
    public static void readFromXML(String file, Storage storage) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        StringBuilder dataXML = new StringBuilder();
        Integer idHelper;

        int i;
        while ((i = bis.read()) != -1) {
            dataXML.append((char) i);
        }
        bis.close();

        XStream xStream = new XStream();
        xStream.processAnnotations(Dragon.class);
        xStream.alias("list", Dragon[].class);
        xStream.addPermission(AnyTypePermission.ANY);


        Dragon[] dragons = null;
        try {
            dragons = (Dragon[]) xStream.fromXML(dataXML.toString());
            idHelper = XMLFileChecker.fileIsCorrect(dragons);
            storage.setIdCounter(idHelper);
        } catch (ConversionException e) {
            OutputMessage.printErrorMessage("\nCan't parse file, data is incorrect");
            System.exit(0);
        } catch (IllegalArgumentException e) {
            OutputMessage.printErrorMessage(e.getMessage());
            System.exit(0);
        }

        for (Dragon dragon : dragons) {
            storage.save(dragon);
        }
    }
}
