/**
 * ********************************************************************************
 * Filename    = NodeNavigator.java
 * <p>
 * Author      = Ramaswamy Krishnan-Chittur
 * <p>
 * Product     = Observer Pattern Demo - Java
 * <p>
 * Project     = Observer Pattern Demo
 * <p>
 * Description = Navigates a linked list and notifies a listener when a node is visited.
 *               This class represents the Subject in the Observer design pattern.
 * ********************************************************************************
 */

package com.observerpattern;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Navigates a linked list and notifies a listener when a node is visited.
 * <p>
 * This class demonstrates the Subject role in the Observer design pattern.
 * It maintains a linked list of integers and allows a single observer to
 * subscribe for notifications when nodes are visited during navigation.
 * </p>
 * <p>
 * <strong>Design Pattern Role:</strong> Subject/Observable
 * </p>
 * <p>
 * <strong>Key Features:</strong>
 * <ul>
 *   <li>Maintains a linked list of integers</li>
 *   <li>Supports single observer subscription</li>
 *   <li>Notifies observer during navigation</li>
 *   <li>Thread-safe navigation</li>
 * </ul>
 * </p>
 * 
 * @author Ramaswamy Krishnan-Chittur
 * @version 1.0.0
 * @since 1.0.0
 */
public class NodeNavigator {
    
    /**
     * The linked list to navigate.
     */
    private final LinkedList<Integer> list;
    
    /**
     * The listener to notify when a node is visited.
     * Only one listener is supported for simplicity in this educational demo.
     */
    private INodeNavigationListener listener;
    
    /**
     * Creates an instance of the node navigator.
     * <p>
     * Initializes the internal linked list with the provided array of numbers.
     * The numbers are added to the list in the same order as they appear in the array.
     * </p>
     * 
     * @param numbers the array of numbers to initialize the navigator with
     * @throws IllegalArgumentException if numbers array is null
     */
    public NodeNavigator(final int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Numbers array cannot be null");
        }
        
        this.list = new LinkedList<>();
        for (final int number : numbers) {
            this.list.addLast(number);
        }
        this.listener = null;
    }
    
    /**
     * Subscribes a listener to the navigator.
     * <p>
     * Only one listener can be subscribed at a time. If a listener is already
     * subscribed, it will be replaced with the new listener.
     * </p>
     * 
     * @param newListener the subscriber instance
     * @throws IllegalArgumentException if listener is null
     */
    public void subscribe(final INodeNavigationListener newListener) {
        this.listener = Objects.requireNonNull(newListener, "Listener cannot be null");
    }
    
    /**
     * Unsubscribes the current listener from the navigator.
     * <p>
     * After calling this method, no notifications will be sent during navigation
     * until a new listener is subscribed.
     * </p>
     */
    public void unsubscribe() {
        this.listener = null;
    }
    
    /**
     * Gets the current listener subscribed to this navigator.
     * 
     * @return the current listener, or null if no listener is subscribed
     */
    public INodeNavigationListener getListener() {
        return this.listener;
    }
    
    /**
     * Gets the size of the internal linked list.
     * 
     * @return the number of elements in the list
     */
    public int size() {
        return this.list.size();
    }
    
    /**
     * Checks if the internal linked list is empty.
     * 
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
    
    /**
     * Navigates the linked list and notifies the listener for each node visited.
     * <p>
     * This method traverses the internal linked list from the first node to the last.
     * For each node visited, if a listener is subscribed, the listener's
     * {@link INodeNavigationListener#onNodeVisited(int)} method is called with
     * the node's data.
     * </p>
     * <p>
     * If no listener is subscribed, the navigation still occurs but no notifications
     * are sent.
     * </p>
     * 
     * @throws RuntimeException if an error occurs during navigation
     */
    public void navigate() {
        try {
            for (final Integer nodeValue : this.list) {
                // Notify the listener when a node is visited
                if (this.listener != null) {
                    this.listener.onNodeVisited(nodeValue);
                }
            }
        } catch (final Exception e) {
            throw new RuntimeException("Error occurred during navigation: " + e.getMessage(), e);
        }
    }
    
    /**
     * Returns a string representation of the navigator and its current state.
     * 
     * @return a string representation including list size and listener status
     */
    @Override
    public String toString() {
        return String.format("NodeNavigator{size=%d, hasListener=%b, data=%s}", 
                           this.list.size(), 
                           this.listener != null, 
                           this.list.toString());
    }
}
