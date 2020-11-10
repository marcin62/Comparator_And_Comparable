import java.util.Comparator;
import java.util.Random;

class BST2
{ 
	Node root;
	Comparator<Car> comparator;
	
    public static void main(String[] args) 
    { 
      BST2 tree = new BST2();
      BST2 tree2 = new BST2(new CouModYeaDist());
      Random rand =new Random();
      
      String model[]= {"Audi","BMW","Toyota","Mercedes","Renault","Pegueot"};
      int year[]= {2012,2008,2016,2005,2009,2020,2018,2001,2003,2002};
      String country[]= {"Polska","Niemcy","Dania","Holandia","Anglia","Hiszpania"};
      int distance[]= {200000,100000,300000,60000,190000,270000,135000};
      for(int i=0;i<10;i++)
      {
    	  Car test = new Car(model[rand.nextInt(model.length)],
    			             year[rand.nextInt(year.length)],
    			             country[rand.nextInt(country.length)],
    			             distance[rand.nextInt(distance.length)]);
    	  tree.insert(new Node(test));
    	  tree2.insert(new Node(test));
      }
      
      	  Car delete = new Car("BMW",2018,"Polska",48000);
      	  tree.insert(new Node(delete));
      	  tree2.insert(new Node(delete));
      	  
      System.out.println("Drzewo 1 przed usunieciem elementu:");
      tree.inorder();
      System.out.println("\nDrzewo 2 przed usunieciem elementu:");
      tree2.inorder();
      
      tree.deleteKey(new Node(delete));
      tree2.deleteKey(new Node(delete));
      System.out.println("\nDrzewo 1 po usunieciu elementu:");
      tree.inorder();
      System.out.println("\nDrzewo 2 po usunieciu elementu:");
      tree2.inorder();
      
    } 
    
    BST2() 
    { 
    	comparator = null;
        root = null; 
    } 
    
    BST2(Comparator<Car> comparator2) 
    { 
    	comparator = comparator2;
        root = null; 
    } 
  
    void deleteKey(Node key) 
    { 
    	if(comparator==null)
        root = deleteRec(root, key); 
    	else
    	root = deleteReccom(root, key);
    } 
  
    Node deleteRec(Node root, Node key) 
    { 
        if (root == null)  return root; 
        if (key.car.compareTo(root.car)<0)
            root.left = deleteRec(root.left, key); 
        else if (key.car.compareTo(root.car)>0) 
            root.right = deleteRec(root.right, key); 
        else
        { 
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 

            root = minValue(root.right); 
            root.right = deleteRec(root.right, root); 
        } 
  
        return root; 
    } 
    Node deleteReccom(Node root, Node key) 
    { 
        if (root == null)  return root; 
        if (comparator.compare(key.car,root.car)<0)
            root.left = deleteReccom(root.left, key); 
        else if (comparator.compare(key.car,root.car)>0) 
            root.right = deleteReccom(root.right, key); 
        else
        { 
            if (root.left == null) 
                return root.right; 
            else if (root.right == null) 
                return root.left; 

            root = minValue(root.right); 
            root.right = deleteRec(root.right, root); 
        } 
  
        return root; 
    } 
  
    Node minValue(Node root) 
    { 
        while (root.left != null) 
        { 
            root = root.left; 
        } 
        return root; 
    } 
  
    void insert(Node key) 
    { 
    	if(comparator==null)
        root = insertRec(root, key); 
    	else
    	root = insertReccom(root,key);
    } 
  
    Node insertRec(Node root, Node key) 
    { 
  
        if (root == null) 
        { 
            root = key; 
            return root; 
        } 
  
        if (key.car.compareTo(root.car)<0) 
            root.left = insertRec(root.left, key); 
        else if (key.car.compareTo(root.car)>0) 
            root.right = insertRec(root.right, key); 
  
        return root; 
    } 
    
    Node insertReccom(Node root, Node key) 
    { 
  
        if (root == null) 
        { 
            root = key; 
            return root; 
        } 
  
        if (comparator.compare(key.car,root.car)<0) 
            root.left = insertReccom(root.left, key); 
        else if (comparator.compare(key.car,root.car)>0) 
            root.right = insertReccom(root.right, key); 
  
        return root; 
    } 
  
    void inorder() 
    { 
        inorderRec(root); 
    } 
  
    void inorderRec(Node root) 
    { 
        if (root != null) 
        { 
            inorderRec(root.left); 
            root.car.printdata();
            inorderRec(root.right); 
        } 
    } 
} 

class Node {
	Car car;
	Node left;
	Node right;
	
	public Node(Car newcar)
	{
		car=newcar;
		left=null;
		right=null;
	}
}

class Car implements Comparable<Car>
{ 
    String model;
    int yearofprod;
    String country;
    int distance;
    
    public Car(String name,int item,String coun,int distance1) { 
    	model=name;
    	yearofprod = item; 
    	country=coun;
    	distance = distance1; 
    }
    
    public void printdata()
    {
    	 System.out.print(this.model + " "+this.yearofprod+" "+this.country+" "+this.distance+"\n"); 
    }
    
	@Override
	public int compareTo(Car o) {
	   	int ret =this.model.compareTo(o.model);
	   	if(ret==0)
	   		return compyea(o);
		return ret;
	} 
	
	public int compyea(Car a){
	    int ret =this.yearofprod-a.yearofprod;
	    if(ret==0)
	    	return compCou(a);
	    else
	    	return ret;
	}
	 
	public int compCou(Car a){
	    int ret =this.country.compareTo(a.country);
	    if(ret==0)
	    	return compDist(a);
	    else
	    	return ret;
	}
	
	public int compDist(Car a){
	    int ret =this.distance-a.distance;
	    return ret;
	}
} 

class CouModYeaDist implements Comparator<Car> 
{ 
    public int compare(Car a, Car b) { 	
    	int ret =a.country.compareTo(b.country);
    	if(ret==0)
    		return compMod(a,b);
    	else
    		return ret;
    } 
    
    public int compyea(Car a, Car b){
    	int ret =a.yearofprod-b.yearofprod;
    	if(ret==0)
    		return compDist(a,b);
    	else
    		return ret;
    }
    
    public int compMod(Car a, Car b){
    	int ret =a.model.compareTo(b.model);
    	if(ret==0)
    		return compyea(a,b);
    	else
    		return ret;
    }
    
    public int compDist(Car a, Car b){
    	int ret =a.distance-b.distance;
    	return ret;
    }
}