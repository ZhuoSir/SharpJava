package xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 *
 * DOM方式解析XML
 */
public class DOMTest {

    public static void main(String[] args) {
//
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder db = factory.newDocumentBuilder();
//            Document document = db.parse("src/main/java/xml/book.xml");
//            NodeList bookList = document.getElementsByTagName("book");
//
//            for (int i = 0; i < bookList.getLength(); i++) {
//                System.out.println("=================下面开始遍历第" + (i + 1) + "本书的内容=================");
//                // 通过 item(i)方法 获取一个book节点，nodelist的索引值从0开始
//                Node book = bookList.item(i);
//                // 获取book节点的所有属性集合
//                NamedNodeMap attrs = book.getAttributes();
//                System.out.println("第 " + (i + 1) + "本书共有" + attrs.getLength() + "个属性");
//
//                // 遍历book的属性
//                for (int j = 0; j < attrs.getLength(); j++) {
//                    // 通过item(index)方法获取book节点的某一个属性
//                    Node attr = attrs.item(j);
//                    // 获取属性名
//                    System.out.println("属性名：" + attr.getNodeName());
//                    // 获取属性值
//                    System.out.println("属性值：" + attr.getNodeValue());
////                    // 获取属性类型
////                    System.out.println("属性类型：" + attr.getNodeType());
//                }
//
//                NodeList childNodes = book.getChildNodes();
//                System.out.println("第" + (i+1) + "本书共有" + childNodes.getLength() + "个子节点");
//
//                for (int j = 0; j < childNodes.getLength(); j++) {
//                    if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
//                        System.out.print("第" + (j) + "个节点的节点名：" + childNodes.item(j).getNodeName());
//                        System.out.println("--节点值是：" + childNodes.item(j).getFirstChild().getNodeValue());
//                    }
//                }
//            }
//
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() throws Exception {
        String sqlName = "query";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse("src/main/java/xml/sqls.xml");
        NodeList nodeList = document.getElementsByTagName("sql");

        Node node = getNode(sqlName, nodeList);
        String result = null;

        if (null != node) {
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    if ("value".equals(childNodes.item(j).getNodeName())) {
                        result = childNodes.item(j).getFirstChild().getNodeValue();
                        break;
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static Node getNode(String sqlName, NodeList nodeList) {
        Node node = null;

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node sql = nodeList.item(i);
            NamedNodeMap map = sql.getAttributes();

            for (int j = 0; j < map.getLength(); j++) {
                Node attr = map.item(j);
                System.out.println("属性名：" + attr.getNodeName());
                System.out.println("属性值：" + attr.getNodeValue());

                if ("name".equals(attr.getNodeName())
                        && sqlName.equals(attr.getNodeValue())) {
                    node = sql;
                    break;
                }
            }
        }

        return node;
    }

}
