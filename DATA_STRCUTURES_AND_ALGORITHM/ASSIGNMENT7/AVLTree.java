

private boolean deleteNode(Node node) {
   
   
    // When Node has two children
    if (node.left != null && node.right != null) {
        Node ss = node.right;
        while (ss.left != null)
            ss = ss.left;
        node.getStock().setSymbol(ss.getStock().getSymbol());
        return deleteNode(ss);
    }

    // when Node is the root
    if (node == root) {
        root = (node.left != null) ? node.left : node.right;
        if (root != null)
            root.parent = null;
    } else {
        // when Node has one child
        Node child = (node.left != null) ? node.left : node.right;
        if (parent.left == node)
            parent.left = child;
        else
            parent.right = child;
        if (child != null)
            child.parent = parent;
    }


}