import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class Exercise2 {
	public static void main(String argv[]) {
		Exercise2 dom = new Exercise2();
		dom.ParseXMLByDOM();
	}

	public void ParseXMLByDOM() {
		try {

			File fXmlFile = new File("C:\\Excercise_2.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root node :"
					+ doc.getDocumentElement().getNodeName());

			NodeList addressNodeList = doc.getElementsByTagName("address");

			System.out.println("----------------------------");

			System.out.println("\nCurrent node :"
					+ addressNodeList.item(0).getNodeName());

			for (int temp = 0; temp < addressNodeList.getLength(); temp++) {

				Node node = addressNodeList.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) node;

					System.out.println("Address : "
							+ eElement.getAttribute("country"));
					System.out.println("Name : "
							+ eElement.getElementsByTagName("name").item(0)
									.getTextContent());
					System.out.println("Street : "
							+ eElement.getElementsByTagName("street").item(0)
									.getTextContent());
					System.out.println("City : "
							+ eElement.getElementsByTagName("city").item(0)
									.getTextContent());
					System.out.println("State : "
							+ eElement.getElementsByTagName("state").item(0)
									.getTextContent());
					System.out.println("Zip: "
							+ eElement.getElementsByTagName("zip").item(0)
									.getTextContent());

				}

			}

			NodeList otherAddressNodeList = doc
					.getElementsByTagName("otherAddress");

			System.out.println("\n----------------------------");
			System.out.println("\nCurrent node :"
					+ otherAddressNodeList.item(0).getNodeName());

			for (int temp = 0; temp < otherAddressNodeList.getLength(); temp++) {

				Node node = otherAddressNodeList.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) node;

					System.out.println("Address : "
							+ eElement.getAttribute("country"));
					System.out.println("Name : "
							+ eElement.getElementsByTagName("name").item(0)
									.getTextContent());
					System.out.println("Street : "
							+ eElement.getElementsByTagName("street").item(0)
									.getTextContent());
					System.out.println("City : "
							+ eElement.getElementsByTagName("city").item(0)
									.getTextContent());
					System.out.println("State : "
							+ eElement.getElementsByTagName("state").item(0)
									.getTextContent());
					System.out.println("Zip: "
							+ eElement.getElementsByTagName("zip").item(0)
									.getTextContent());

				}
			}

			NodeList employeesNodeList = doc.getElementsByTagName("employees");
			NodeList employeeNodeList = doc.getElementsByTagName("employee");
			System.out.println("\n----------------------------");
			System.out.println("\nCurrent node :"
					+ employeesNodeList.item(0).getNodeName());
			for (int temp = 0; temp < employeeNodeList.getLength(); temp++) {

				Node node = employeeNodeList.item(temp);

				System.out.println("\nChild node :"
						+ node.getNodeName());
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					System.out.println("Division : "
							+ eElement.getAttribute("division"));
					System.out.println("Name : "
							+ eElement.getElementsByTagName("fullName").item(0)
									.getTextContent());
					System.out.println("Years Of Experience : "
							+ eElement
									.getElementsByTagName("yearsOfExperience")
									.item(0).getTextContent());
					System.out.println("Title : "
							+ eElement.getElementsByTagName("title").item(0)
									.getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
