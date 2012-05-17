package pl.mgrproject.api;

public class Edge<T> {

    public int first;
    public int last;
    public T value;

    public Edge(int first, int last, T value) {
	this(first, last);
	this.value = value;
    }

    public Edge(int first, int last) {
	this.first = first;
	this.last = last;
	this.value = null;
    }

    @Override
    public boolean equals(Object o) {
	if (!(o instanceof Edge)) {
	    return false;
	}
	Edge e = (Edge) o;
	if (value != null) {
	    if (e.first != first || e.last != last || e.value != value) {
		return false;
	    }
	} else {
	    if (e.first != first || e.last != last) {
		return false;
	    }
	}

	return true;
    }

}
