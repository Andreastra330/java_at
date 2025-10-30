package ru.stqa.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ru.stqa.model.ContactData;
import ru.stqa.model.GroupData;
import ru.stqa.utils.Utils;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Generator {
    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-c"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private void save(Object data) throws IOException {

        String outputDir = "src/test/resources/GeneratorFiles";
        File outputFile = new File(output);
        if (!outputFile.getPath().contains(File.separator)) {
            outputFile = new File(outputDir, output);
        }

        if ("json".equals(format)) {
            ObjectMapper mapper = JsonMapper.builder()
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .build();
            mapper.writeValue(outputFile, data);

        } else if ("yaml".equals(format)) {
            new YAMLMapper().writeValue(outputFile, data);

        } else if ("xml".equals(format)) {
            new XmlMapper().writeValue(outputFile, data);

        } else {
            throw new IllegalArgumentException("Неизвестный формат: " + format);
        }
//        if("json".equals(format)) {
//            ObjectMapper mapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).build();
//            var json =  mapper.writeValueAsString(data);
//            try (var writer = new FileWriter(output))
//            {
//            writer.write(json);
//            }
//        } else if ("yaml".equals(format)) {
//            var mapper = new YAMLMapper();
//            mapper.writeValue(new File(output),data);
//        } else if ("xml".equals(format)) {
//            var mapper = new XmlMapper();
//            mapper.writeValue(new File(output),data);
//        } else {throw new IllegalArgumentException(" Неизвестный формат " + format);}

    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип" + type);
        }
    }


    private Object generateGroups() {
        var result = new ArrayList<GroupData>();

        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(Utils.randomString(i * 10))
                    .withHeader(Utils.randomString(i * 10))
                    .withFooter(Utils.randomString(i * 10)));
        }
        return result;
    }

    private Object generateContacts() {
        var result = new ArrayList<ContactData>();

        for (int i = 0; i < count; i++) {
            result.add(new ContactData()
                    .withFirstName(Utils.randomString(i * 10))
                    .withLastName(Utils.randomString(i * 10))
                    .withAddress(Utils.randomString(i * 10))
                    .withEmail(Utils.randomString(i * 10))
                    .withWorkPhone(Utils.randomInt(i * 2))
                    .withPhoto(Utils.randomFile("src/test/resources/images"))
            );
        }
        return result;
    }
}
