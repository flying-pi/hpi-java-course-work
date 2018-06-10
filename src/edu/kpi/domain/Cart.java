package edu.kpi.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<CartItem>  items = new LinkedList<>();

    public void add(CartItem item) {
        items.add(item);
    }

    public List<CartItem> getItems(){
        return new ArrayList<>(items);
    }

    public int getCount() {
        return items.size();
    }

    public void remove(int pos) {
        items.remove(pos);
    }
}
