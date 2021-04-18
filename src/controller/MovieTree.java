package controller;
import model.Node;

public class MovieTree {

	private Node root;


	public void insert(String movieName, String leadActor, int ratingOfMovie, String picUrl, String trailerUrl) {
		if (root == null) {
			root = new Node(movieName, leadActor, ratingOfMovie, picUrl, trailerUrl);
		} else {
			root.insert(movieName, leadActor, ratingOfMovie, picUrl, trailerUrl);
		}
	}

	public Node get(String movieName) {
		if (root != null) {
			return root.get(movieName);
		}

		return null;
	}

	public void traverseInOrder() {
		
		if (root != null) {
			root.traverseInOrder();	
		}
	}
	
	public void traversePostOrder() {
		
		if (root != null) {
			root.traversePostOrder();	
		}
	}
	
	public void traversePreOrder() {
		
		if (root != null) {
			root.traversePreOrder();	
		}
	}
	
	public int getSize() {
		if (root != null)
			return root.getSize();
		else
			return 0;
	}
	
	public int getHeight() {
		if (root != null)
			return root.getHeight();
		else
			return 0;
	}
	
    public void delete(String value) {
        root = delete(root, value);
    }

    private Node delete(Node subtreeRoot, String value) {
        if (subtreeRoot == null) {
            return subtreeRoot;
        }

        if (value.compareTo(subtreeRoot.getMovieNameAsKey()) <0 ) {
            subtreeRoot.setLeftChhild(delete(subtreeRoot.getLeftChhild(), value));
        }
        else if (value.compareTo(subtreeRoot.getMovieNameAsKey()) > 0) {
            subtreeRoot.setRightChhild(delete(subtreeRoot.getRightChhild(), value));
        }
        else {
            // Cases 1 and 2: node to delete has 0 or 1 child(ren)
            if (subtreeRoot.getLeftChhild() == null) {
                return subtreeRoot.getRightChhild();
            }
            else if (subtreeRoot.getRightChhild() == null) {
                return subtreeRoot.getLeftChhild();
            }
        }

        return subtreeRoot;
    }

}
