import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Exercise1 {
	public static void main(String argv[]) {		 
	    try {	 
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
	 
		DefaultHandler handler = new DefaultHandler() {
	 
		@Override
		public void startElement(String uri, String localName,String qName, 
	                Attributes attributes) throws SAXException {
	 
			System.out.println("Start Element :" + qName);
	 
		}
	 
		@Override
		public void endElement(String uri, String localName,
			String qName) throws SAXException {
	 
			System.out.println("End Element :" + qName);
	 
		}
	 
		@Override
		public void characters(char ch[], int start, int length) throws SAXException {
	 
			String content = new String(ch, start, length);
			if(!content.isEmpty() && !content.contains("\n")){
				System.out.println("Content : " + content);
			}
	 
		}
	 
	     };
	 
	       saxParser.parse("C:\\Excercise_1.xml", handler);
	 
	     } catch (Exception e) {
	       e.printStackTrace();
	     }
	 
	   }

}
