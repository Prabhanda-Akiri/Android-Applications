/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.touringmusician;


import android.graphics.Point;

import java.util.Iterator;

public class CircularLinkedList implements Iterable<Point> {

    private class Node {
        Point point;
        Node prev, next;
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
    }

    Node head=null;

    public void insertBeginning(Point p)
    {
        Node newNode = new Node();
        newNode.point=p;

        if(head==null)
        {
            newNode.prev=newNode;
            newNode.next=newNode;

        }
        else
        {
            newNode.next=head;
            newNode.prev=head.prev;
            head.prev.next=newNode;
            head.prev=newNode;

        }
        head=newNode;

    }

    private float distanceBetween(Point from, Point to) {
        return (float) Math.sqrt(Math.pow(from.y-to.y, 2) + Math.pow(from.x-to.x, 2));
    }

    public float totalDistance() {
        float total = 0;
        Node st=head,temp=null,prs=null;
        while(temp!=head)
        {
            if (temp==null)
            {    temp=head;
                prs=head;
                temp=temp.next;}
            else
            {
                total=total+distanceBetween(temp.point,prs.point);
                prs=temp;
                temp=temp.next;
            }
        }
        return total;
    }

    public void insertNearest(Point p) {

        Node newNode = new Node();
        newNode.point=p;

        if(head==null) {
            newNode.prev = newNode;
            newNode.next = newNode;
            head=newNode;
        }

        else
        {
            Node min=null,temp=null;
            float min_d=0,x;

            while(temp!=head)
            {
                if(temp==null)
                {
                    temp=head;
                    min=head;
                    min_d=distanceBetween(head.point,p);
                    //if(temp.next!=null)
                    temp=temp.next;

                }
                else
                {
                    x=distanceBetween(temp.point,p);
                    if(x<min_d)
                    {
                        min=temp;
                        min_d=x;

                    }
                    temp=temp.next;
                }
            }

            newNode.next=min;
            newNode.prev=min.prev;
            min.prev.next=newNode;
            min.prev=newNode;



        }



    }

    public void insertSmallest(Point p) {


        Node newNode = new Node();
        newNode.point=p;

        if(head==null) {
            newNode.prev = newNode;
            newNode.next = newNode;
            head=newNode;
        }

        else
        {
            Node min=null,temp=null;
            float min_d=0,x,x_prev=0,added=0;

            while(temp!=head)
            {
                if(temp==null)
                {
                    temp=head;
                    min=head;
                    min_d=distanceBetween(head.point,p)+distanceBetween(p,head.next.point);
                    //if(temp.next!=null)
                    if(head.next!=head) {
                        x_prev = distanceBetween(head.point, head.next.point);
                        added = min_d - x_prev;
                        temp = temp.next;
                    }
                }
                else
                {
                    x=distanceBetween(temp.point,p)+distanceBetween(p,temp.next.point)-distanceBetween(temp.point,temp.next.point);
                    if(x<added)
                    {
                        min=temp;
                        added=x;

                    }
                    temp=temp.next;
                }
            }
            newNode.next=min.next;
            newNode.prev=min;
            min.next.prev=newNode;
            min.next=newNode;
        }
    }

    public void reset() {
        head = null;
    }

    private class CircularLinkedListIterator implements Iterator<Point> {

        Node current;

        public CircularLinkedListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Point next() {
            Point toReturn = current.point;
            current = current.next;
            if (current == head) {
                current = null;
            }
            return toReturn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return new CircularLinkedListIterator();
    }


}
