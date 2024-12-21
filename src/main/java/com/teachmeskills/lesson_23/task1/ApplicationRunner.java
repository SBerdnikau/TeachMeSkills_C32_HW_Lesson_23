package com.teachmeskills.lesson_23.task1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teachmeskills.lesson_23.task1.model.Book;
import com.teachmeskills.lesson_23.task1.model.BookListWrapper;
import com.teachmeskills.lesson_23.task1.parser.XMLParser;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.List;

public class ApplicationRunner {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Reading a JSON file
            List<Book> books = objectMapper.readValue(new File("books.json"),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));

            // Saving to XML
            JAXBContext jaxbContext = JAXBContext.newInstance(BookListWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new BookListWrapper(books), new File("books.xml"));

            //read XML and find the book with the most pages
            XMLParser xmlParser = new XMLParser();
            xmlParser.parseXML("books.xml");
        }catch (Exception e){
            System.out.println("General JSON to XML Conversion Error: " + e.getMessage());
        }
    }
}
