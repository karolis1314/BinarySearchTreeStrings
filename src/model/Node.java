package model;

public class Node {
	String movieNameAsKey;
	String leadActor;
	int rating;
	String moviePicUrl;
	String movieTrailerUrl;
	private Node leftChhild;
	private Node rightChhild;


	public Node(String key, String actor, int rating, String pic, String trailer) {
		this.movieNameAsKey = key;
		this.leadActor = actor;
		this.rating = rating;
		this.moviePicUrl = pic;
		this.movieTrailerUrl = trailer;

	}

	public void insert(String movieName, String leadActor, int ratingOfMovie, String picUrl, String trailerUrl) {
		if (movieName.equals(movieNameAsKey)) {
			return;
		}
		if (movieName.compareTo(movieNameAsKey) < 0) {
			if (leftChhild == null) {
				leftChhild = new Node(movieName, leadActor, ratingOfMovie, picUrl, trailerUrl);
			} else {
				leftChhild.insert(movieName, leadActor, ratingOfMovie, picUrl, trailerUrl);
			}
		} else {
			if (rightChhild == null) {
				rightChhild = new Node(movieName, leadActor, ratingOfMovie, picUrl, trailerUrl);
			} else {
				rightChhild.insert(movieName, leadActor, ratingOfMovie, picUrl, trailerUrl);
			}
		}
	}

	public Node get(String movieName) {
		if (movieName.equals(movieNameAsKey)) {
			return this;
		}
		if (movieName.compareTo(movieNameAsKey) < 0) {
			if (leftChhild != null) {
				return leftChhild.get(movieName);
			}
		} else {
			if (rightChhild != null) {
				return rightChhild.get(movieName);
			}
		}
		return null;
	}

//Printing the tree as in Order traverse, first selecting all from left to parent node, then parent node, then right side of the parent node;
	public void traverseInOrder() {

		if (leftChhild != null) {
			leftChhild.traverseInOrder();
		}
		System.out.println(
				"Movie name: " + movieNameAsKey + "\n" + "Lead actor is: " + leadActor + "\n" + "The movie is rated: "
						+ rating + "\n" + "Trailel URL:\n " + movieTrailerUrl + "\n" + "*****************");
		if (rightChhild != null) {
			rightChhild.traverseInOrder();
		}
	}

	public void traversePostOrder() {

		System.out.println(
				"Movie name: " + movieNameAsKey + "\n" + "Lead actor is: " + leadActor + "\n" + "The movie is rated: "
						+ rating + "\n" + "Trailel URL:\n " + movieTrailerUrl + "\n" + "*****************");

		if (leftChhild != null) {
			leftChhild.traversePostOrder();
		}

		if (rightChhild != null) {
			rightChhild.traversePostOrder();
		}

	}

	public void traversePreOrder() {

		if (leftChhild != null) {
			leftChhild.traversePreOrder();
		}

		if (rightChhild != null) {
			rightChhild.traversePreOrder();
		}
		System.out.println(
				"Movie name: " + movieNameAsKey + "\n" + "Lead actor is: " + leadActor + "\n" + "The movie is rated: "
						+ rating + "\n" + "Trailel URL:\n " + movieTrailerUrl + "\n" + "*****************");
	}
	
	public int getSize() {
		int leftSize = 0, rightSize = 0;
		
		if (this.leftChhild != null)
			leftSize = this.leftChhild.getSize();
					
		if (this.rightChhild != null)
			rightSize = this.rightChhild.getSize();
		
		return 1 + leftSize + rightSize;			
	}
	
	public int getHeight() {
		int leftHeight = 0, rightHeight = 0;
		
		if (this.leftChhild != null)
			leftHeight = this.leftChhild.getHeight();
					
		if (this.rightChhild != null)
			rightHeight = this.rightChhild.getHeight();
		
		return 1 + Math.max(leftHeight, rightHeight);
	}

//Setters and Getters for the model
	public String getMovieNameAsKey() {
		return movieNameAsKey;
	}

	public void setMovieNameAsKey(String movieNameAsKey) {
		this.movieNameAsKey = movieNameAsKey;
	}

	public String getLeadActor() {
		return leadActor;
	}

	public void setLeadActor(String leadActor) {
		this.leadActor = leadActor;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getMoviePicUrl() {
		return moviePicUrl;
	}

	public void setMoviePicUrl(String moviePicUrl) {
		this.moviePicUrl = moviePicUrl;
	}

	public String getMovieTrailerUrl() {
		return movieTrailerUrl;
	}

	public void setMovieTrailerUrl(String movieTrailerUrl) {
		this.movieTrailerUrl = movieTrailerUrl;
	}

	public Node getLeftChhild() {
		return leftChhild;
	}

	public void setLeftChhild(Node leftChhild) {
		this.leftChhild = leftChhild;
	}

	public Node getRightChhild() {
		return rightChhild;
	}

	public void setRightChhild(Node rightChhild) {
		this.rightChhild = rightChhild;
	}

}