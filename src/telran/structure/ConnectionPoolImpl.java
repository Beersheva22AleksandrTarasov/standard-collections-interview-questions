package telran.structure;

import java.util.HashMap;

public class ConnectionPoolImpl implements CollectionsPool {
	static class Node {
		Connection connection;
		Node next;
		Node prev;

		public Node(Connection connection) {
			this.connection = connection;
		}
	}

	static class ConnectionsList {
		Node head;
		Node tail;

		public void addHead(Node connection) {
			connection.next = head;
			if (head != null) {
				head.prev = connection;
			} else {
				tail = connection;
			}
			head = connection;
		}

		public void removeTail() {
			tail = tail.prev;
			if (tail != null) {
				tail.next = null;
			}
		}

		public void removeNode(Node connection) {
			if (connection == tail) {
				removeTail();
			} else {
				Node previous = connection.prev;
				Node following = connection.next;
				following.prev = previous;
				previous.next = following;
			}
		}
	}

	ConnectionsList connectionsList = new ConnectionsList();
	HashMap<Integer, Node> connections = new HashMap<>();
	int connectionsLimit;

	public ConnectionPoolImpl(int connectionsLimit) {
		this.connectionsLimit = connectionsLimit;
	}

	@Override
	public boolean addConnection(Connection connection) {
		if (connections.containsKey(connection.getId())) {
			return false;
		}
		Node nodeConnection = new Node(connection);
		addMapList(connection, nodeConnection);
		if (connections.size() > connectionsLimit) {
			removeListTailMap();
		}

		return true;
	}

	private void removeListTailMap() {
		connections.remove(connectionsList.tail.connection.getId());
		connectionsList.removeTail();
	}

	private void addMapList(Connection connection, Node nodeConnection) {
		connections.put(connection.getId(), nodeConnection);
		connectionsList.addHead(nodeConnection);
	}

	@Override
	public Connection getConnection(int id) {
		Node nodeConnection = connections.get(id);
		if (nodeConnection == null) {
			return null;
		}
		moveConnectionToHead(nodeConnection);
		return nodeConnection.connection;
	}

	private void moveConnectionToHead(Node nodeConnection) {
		if (nodeConnection != connectionsList.head) {
			connectionsList.removeNode(nodeConnection);
			connectionsList.addHead(nodeConnection);
		}
	}
}
