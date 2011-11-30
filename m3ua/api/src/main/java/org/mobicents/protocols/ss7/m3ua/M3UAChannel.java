/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.ss7.m3ua;

import java.io.IOException;
import java.net.SocketAddress;
import org.mobicents.protocols.ss7.m3ua.message.M3UAMessage;

/**
 * A selectable channel for message connecting sockets.
 * 
 * @author kulikov
 */
public interface M3UAChannel extends M3UASelectableChannel {

    /**
     * Binds the channel to a local address.
     * 
     * @param address the SocketAddress to bind to
     * @throws java.io.IOException
     */
    public void bind(SocketAddress address) throws IOException;
    
    /**
     * Connects this channel's.
     * 
     * @param remote The remote address to which this channel is to be connected
     * @return if a connection was established, false if this channel is in non-blocking mode and the 
     * connection operation is in progress.
     * @throws java.io.IOException
     */
    public boolean connect(SocketAddress remote) throws IOException;
    
    /**
     * Finishes the process of connecting a channel.
     * 
     * A non-blocking connection operation is initiated by invoking its connect method. 
     * Once the connection is established, or the attempt has failed, the channel will become connectable 
     * and this method may be invoked to complete the connection sequence. If the connection operation 
     * failed then invoking this method will cause an appropriate IOException to be thrown.
     * 
     * 
     * @return true if, and only if, this channel is now connected
     * @throws java.io.IOException
     */
    public boolean finishConnect() throws IOException;
    
    /**
     * Closes this channel.
     * 
     * If the channel has already been closed then this method returns immediately. 
     * @throws java.io.IOException
     */
    public void close() throws IOException;
    
    /**
     * Reads M3UA message from channel when channel is ready for operation READ..
     * 
     * @return return the received message.
     * @throws java.io.IOException
     */
    public M3UAMessage receive() throws IOException;
    
    /**
     * Sends M3UA message when channel is ready for operation WRITE.
     * 
     * @param message the message to send
     * @throws java.io.IOException
     */
    public void send(M3UAMessage message) throws IOException;
    
    /**
     * Tells whether or not this channel is connected.
     * 
     * @return true if, and only if, this channel is connected
     */
    public boolean isConnected() throws IOException;

    /**
     * Tells whether or not a connection operation is in progress on this channel.
     * 
     * @return true if, and only if, a connection operation has been initiated on this channel but not yet 
     * completed by invoking the finishConnect method
     */
    public boolean isConnectionPending();
    
}