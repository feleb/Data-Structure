package eg.edu.alexu.csd.filestructure.avl;
 
import java.io.File;
import eg.edu.alexu.csd.filestructure.avl.AVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;
import eg.edu.alexu.csd.filestructure.avl.Node;
 
public class Trial1 {
 
    public static void main(String[] args) {
//    	Dictionary dictionary = new Dictionary();
//    	dictionary.load(new File("C:\\Users\\karem\\Downloads\\gg.txt"));
//    	System.out.println("TEST INSERTING NEW WORD");
//    	System.out.println(dictionary.insert("EMAD"));
//    	System.out.println("TEST ISERTING AN EXIST WORD");
//    	System.out.println(dictionary.insert("KAREM"));
//    	System.out.println("TEST If Word Is Exist or Not");
//    	System.out.println(dictionary.exists("EMAD"));
//    	System.out.println(dictionary.exists("CAR"));
//    	System.out.println("Test deleting an exist word and search for it after deletion");
//    	System.out.println(dictionary.delete("EMAD"));
//    	System.out.println(dictionary.exists("EMAD"));
//    	System.out.println("Test deleting a word not exist in the Dictionary");
//    	System.out.println(dictionary.delete("CAR"));
//    	System.out.println("TEST The Size OF the dictionary");
//    	System.out.println(dictionary.size);
//    	System.out.println("Test the Height of the Dictionary");
//    	System.out.println(dictionary.height());
    	
//    	dictionary.insert("This is A String");
//    	dictionary.insert("This is A String");
//    	dictionary.load(new File("C:\\Users\\karem\\Downloads\\gg.txt"));
////    	System.out.println(dictionary.delete("5"));
////    	System.t.println(dictionary.size());
//    	System.out.println(dictionary.height());
//    	System.out.println(dictionary.exists("5"));
//        AVLTree avl = new AVLTree();
//        avl.insert(5);
        //TestCase 1
        /*AVLTree avl = new AVLTree();
        avl.insert(14);
        avl.insert(17);
        avl.insert(11);
        avl.insert(7);
        avl.insert(4);
        avl.insert(53);
        avl.insert(13);
        avl.insert(12);
        avl.insert(8);
        avl.delete(53);
        avl.delete(11);
        avl.delete(8);
        avl.inOrder((Node)avl.getTree());
        */
        //TestCase2
        /*AVLTree avl = new AVLTree(); 
        avl.insert(15);
        avl.insert(20);
        avl.insert(24);
        avl.insert(10);
        avl.insert(13);
        avl.insert(7);
        avl.insert(30);
        avl.insert(36);
        avl.insert(25);
        avl.delete(24);
        avl.delete(20);
        avl.inOrder((Node)avl.getTree());
        */
    	//TestCase3
    	AVLTree avl = new AVLTree();
    	avl.insert(8);
        avl.insert(4);
        avl.insert(10);
        avl.insert(9);
        avl.insert(12);
        avl.delete(4);
      // avl.inOrder((Node)avl.getTree());
      System.out.println(avl.searchReturnsNode(10).getRightChild().getValue());
       System.out.println(avl.searchReturnsNode(10).getLeftChild().getRightChild().getValue());
        System.out.println(avl.searchReturnsNode(10).getLeftChild().getValue());

       /*
        System.out.println("parent of 24: " + avl.searchReturnsNode(24).getParent().getValue());
        System.out.println("parent of 20: " + avl.searchReturnsNode(20).getParent().getValue());
        System.out.println("parent of 15: " + avl.searchReturnsNode(15).getParent().getValue());
        System.out.println("left child of 13: " + avl.searchReturnsNode(13).getLeftChild().getValue());
        System.out.println("right child of 13: " + avl.searchReturnsNode(13).getRightChild().getValue());
        System.out.println(
                "\nright child of 15's parent: " + avl.searchReturnsNode(15).getParent().getRightChild().getValue());
        System.out.println(
                "\nleft child of 15's parent: " + avl.searchReturnsNode(15).getParent().getLeftChild().getValue());
        System.out.println();
        System.out.println("parent of 10: " + avl.searchReturnsNode(10).getParent().getValue());
        System.out.println("left child of 13:" + avl.searchReturnsNode(13).getLeftChild().getValue());
        System.out.println("right child of 13: " + avl.searchReturnsNode(13).getRightChild().getValue());
 
        System.out.println("parent of 7: " + avl.searchReturnsNode(7).getParent().getValue());
        System.out.println("parent of 25: " + avl.searchReturnsNode(25).getParent().getValue());
        System.out.println("parent of 30: " + avl.searchReturnsNode(30).getParent().getValue());
        System.out.println("parent of 36: " + avl.searchReturnsNode(36).getParent().getValue());
        */
    }
 
}