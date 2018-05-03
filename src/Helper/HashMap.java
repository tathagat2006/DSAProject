package Helper;


import java.util.ArrayList;

import Helper.GenericLinkedList;

public class HashMap<K, V> {
	private class Pair {
		K key;
		V value;
	}

	private GenericLinkedList<Pair>[] bucketArr; // N
	private int size; // n

	public HashMap() {
		this(5);
	}
	
	public HashMap(int cap) {
		this.bucketArr = (GenericLinkedList<Pair>[]) new GenericLinkedList[cap];
		for (int i = 0; i < this.bucketArr.length; i++) {
			this.bucketArr[i] = new GenericLinkedList<Pair>();
		}

		this.size = 0;
	}

	public void put(K key, V value) throws Exception {
		int bi = this.hashFunction(key);
		GenericLinkedList<Pair> bucket = this.bucketArr[bi];
		int foundAt = this.findInBucket(bucket, key);

		if (foundAt == -1) {
			Pair np = new Pair();
			np.key = key;
			np.value = value;

			bucket.addLast(np);
			this.size++;
		} else {
			Pair p2bu = bucket.getAt(foundAt);
			p2bu.value = value;
		}

		// rehashing
		double lambda = (1.0 * this.size) / this.bucketArr.length;
		if (lambda > 2.0) {
			this.rehash();
		}
	}

	public boolean containsKey(K key) throws Exception {
		int bi = this.hashFunction(key);
		GenericLinkedList<Pair> bucket = this.bucketArr[bi];
		int foundAt = this.findInBucket(bucket, key);

		if (foundAt == -1) {
			return false;
		} else {
			return true;
		}
	}

	public V get(K key) throws Exception {
		int bi = this.hashFunction(key);
		GenericLinkedList<Pair> bucket = this.bucketArr[bi];
		int foundAt = this.findInBucket(bucket, key);

		if (foundAt == -1) {
			return null;
		} else {
			Pair p2br = bucket.getAt(foundAt);
			return p2br.value;
		}
	}

	public V remove(K key) throws Exception {
		int bi = this.hashFunction(key);
		GenericLinkedList<Pair> bucket = this.bucketArr[bi];
		int foundAt = this.findInBucket(bucket, key);

		if (foundAt == -1) {
			return null;
		} else {
			Pair p2br = bucket.removeAt(foundAt);
			this.size--;
			return p2br.value;
		}
	}

	private int hashFunction(K key) {
		int hc = key.hashCode(); // discusion pending
		return Math.abs(hc) % this.bucketArr.length;
	}

	private int findInBucket(GenericLinkedList<Pair> bucket, K key) throws Exception {
		for (int i = 0; i < bucket.size(); i++) {
			Pair temp = bucket.getAt(i);
			if (temp.key.equals(key)) {
				return i;
			}
		}

		return -1;
	}

	private void rehash() throws Exception {
		GenericLinkedList<Pair>[] oba = this.bucketArr;

		// cleaning
		this.bucketArr = (GenericLinkedList<Pair>[]) new GenericLinkedList[2 * oba.length];
		for (int i = 0; i < this.bucketArr.length; i++) {
			this.bucketArr[i] = new GenericLinkedList<Pair>();
		}
		this.size = 0;

		// refilling
		for (int i = 0; i < oba.length; i++) {
			for (int j = 0; j < oba[i].size(); j++) {
				Pair p = oba[i].getAt(j);
				this.put(p.key, p.value);
			}
		}
	}

	public void display() throws Exception {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		GenericLinkedList<Pair>[] ba = this.bucketArr;

		for (int i = 0; i < ba.length; i++) {
			System.out.print("BUCKET " + i + ": => ");
			for (int j = 0; j < ba[i].size(); j++) {
				Pair p = ba[i].getAt(j);
				System.out.print("[" + p.key + "@" + p.value + "], ");
			}
			System.out.println(".");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	public ArrayList<K> keySet() throws Exception {
		ArrayList<K> rv = new ArrayList<>();

		GenericLinkedList<Pair>[] ba = this.bucketArr;
		for (int i = 0; i < ba.length; i++) {
			for (int j = 0; j < ba[i].size(); j++) {
				Pair p = ba[i].getAt(j);
				rv.add(p.key);
			}
		}

		return rv;
	}
}
