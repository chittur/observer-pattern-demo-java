/**
 * ********************************************************************************
 * Filename    = INodeNavigationListener.java
 * <p>
 * Author      = Ramaswamy Krishnan-Chittur
 * <p>
 * Product     = Observer Pattern Demo - Java
 * <p>
 * Project     = Observer Pattern Demo
 * <p>
 * Description = Contract for a listener that is notified when a node is visited.
 *               This interface represents the Observer in the Observer design pattern.
 * ********************************************************************************
 */

package com.observerpattern;

/**
 * Interface for a listener that is notified when a node is visited.
 * <p>
 * This interface defines the contract for observers in the Observer design pattern.
 * Any class that implements this interface can be notified when the NodeNavigator
 * visits a node during navigation.
 * </p>
 * <p>
 * <strong>Design Pattern Role:</strong> Observer Interface
 * </p>
 * 
 * @author Ramaswamy Krishnan-Chittur
 * @version 1.0.0
 * @since 1.0.0
 */
public interface INodeNavigationListener {
    
    /**
     * Called when a node is visited during navigation.
     * <p>
     * This method is invoked by the NodeNavigator whenever it visits a node
     * in the linked list. Implementing classes should define the specific
     * behavior they want to perform when a node is visited.
     * </p>
     * 
     * @param data the data of the node being visited
     */
    void onNodeVisited(int data);
}
