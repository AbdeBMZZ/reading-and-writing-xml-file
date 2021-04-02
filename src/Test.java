import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import models.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class Test {
	
	public Test() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("books.xml");
			doc.getDocumentElement();
			NodeList books = doc.getElementsByTagName("book");
			float max = -1;
			List<Book> ktouba = new ArrayList<Book>();
			for(int i=0;i<books.getLength();i++) {
				
				Node book = books.item(i);
				
				if(book.getNodeType()==Node.ELEMENT_NODE) {
					Element Ebook = (Element) book;
					NodeList childs = Ebook.getChildNodes();
					
					int curPrice = Integer.parseInt(Ebook.getAttribute("price"));
					
					if(curPrice >100) {
						System.out.println(curPrice);
						Book ktab = new Book();
						for(int j=0;j<childs.getLength();j++) {
							
							Node n = childs.item(j);
							if(n.getNodeType()==Node.ELEMENT_NODE) {
								Element p2 = (Element) n;
								if(p2.getNodeName().equals("title")) {
									ktab.setTitle(p2.getTextContent());
								}
								if(p2.getNodeName().equals("price")) {
									ktab.setPrice(p2.getTextContent());
								}

								
							}
						}
						ktouba.add(ktab);
					}

				}
			}
			

			System.out.println(max);
			ktouba.forEach((n) -> {
				System.out.println(n.getTitle());
			});
			createXml(ktouba);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void createXml(List<Book> e) {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		 
        DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentFactory.newDocumentBuilder();
	        Document document = documentBuilder.newDocument();
	        
	        Element root = document.createElement("books");
	        document.appendChild(root);
	        
	        e.forEach((n) -> {
				Element book = document.createElement("book");
				book.setAttribute("price", n.getPrice());
				root.appendChild(book);
				Element title = document.createElement("Title");
				Text textTitle = document.createTextNode(n.getTitle());
				title.appendChild(textTitle);
				book.appendChild(title);
				
				Element price = document.createElement("price");
				Text textPrice = document.createTextNode(n.getPrice());
				price.appendChild(textPrice);
				book.appendChild(price);

	        });
	        
			TransformerFactory transformerFactory=TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 5);
			Transformer transformer=transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource=new DOMSource(document);
			StreamResult streamResult=new StreamResult(new File("database/output.xml"));
			transformer.transform(domSource, streamResult);
			
		} catch (ParserConfigurationException | TransformerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		new Test();
	}

}
