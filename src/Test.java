import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
			for(int i=0;i<books.getLength();i++) {
				
				Node book = books.item(i);
				
				if(book.getNodeType()==Node.ELEMENT_NODE) {
					Element Ebook = (Element) book;
						NodeList childs = Ebook.getChildNodes();

						for(int j=0;j<childs.getLength();j++) {
							
							Node n = childs.item(j);
							if(n.getNodeType()==Node.ELEMENT_NODE) {
								Element p2 = (Element) n;
								if (p2.getNodeName().equals("price")) {
									float currentPrice = Float.parseFloat(p2.getTextContent());
									if(currentPrice>max)
										max =currentPrice;
									
								}
							}
						}


				}
			}
			
			System.out.println(max);
			Element isHim = null;
			for(int i=0;i<books.getLength();i++) {
				Node book = books.item(i);
				
				if(book.getNodeType()==Node.ELEMENT_NODE) {
					Element Ebook = (Element) book;
						NodeList childs = Ebook.getChildNodes();
						for(int j=0;j<childs.getLength();j++) {
							
							Node n = childs.item(j);
							if(n.getNodeType()==Node.ELEMENT_NODE) {
								Element p2 = (Element) n;
	
								if(p2.getNodeName().equals("price")) {
									if(Float.parseFloat(p2.getTextContent())==max) {
										isHim=Ebook;
										break;
									}
								}
							}
						}
				}
			}
			
			NodeList bookInfo = isHim.getChildNodes();
			
			for(int h=0;h<bookInfo.getLength();h++) {
				Node p = bookInfo.item(h);
				
				if(p.getNodeType()==Node.ELEMENT_NODE) {
					Element e6 = (Element) p;
					
					if(e6.getNodeName().equals("title")) {
						System.out.println(e6.getTextContent());
					}
				}
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Test();
	}

}
