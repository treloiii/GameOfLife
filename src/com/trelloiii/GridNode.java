package com.trelloiii;

import java.io.Console;
import java.util.*;

public class GridNode {
    private Console console;
    private Node[][] grid;
    private int size;
    private int checkInRow=0;
    GridNode(int size,int bound){
        console=System.console();
        this.size=size;
        grid=new Node[size][size];
        fillGrid(bound);
    }
    GridNode(int size, List<KeyVal> values){
        console=System.console();
        this.size=size;
        grid=new Node[size][size];
        fillGrid(values);
    }

    private void fillGrid(int bound){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                Node node=new Node();
                Random r=new Random();
                int rand=r.nextInt(bound);
                if(rand==1){
                    node.alive();
                }
                this.grid[i][j]=node;
            }
        }
    }
    private void fillGrid(List<KeyVal> values){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                this.grid[i][j]=new Node();
            }
        }
        for (KeyVal keyVal:values) {
            this.grid[keyVal.getI()][keyVal.getJ()].alive();
        }
    }

    public void newGeneration() throws Exception {
        Node[][] copyOfGrid= Arrays.copyOf(grid,grid.length);
        List<Node> forKill=new ArrayList<>();
        List<Node> forLive=new ArrayList<>();
        for(int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                int neighbours=0;
                if(i>0&&i<size-1){//если мы не на верхней и не на нижней строчке
                    neighbours=checkIfMiddle(i,j);
                }
                else if(i==0){//если на верхней строке
                    neighbours=checkIfTop(i,j);
                }
                else{//если на нижней строке
                    neighbours=checkIfBottom(i,j);
                }

                if(neighbours>3||neighbours<2){
                    if(grid[i][j].isAlive())
                        forKill.add(grid[i][j]);
                }
                else {
                    if(!grid[i][j].isAlive())
                        forLive.add(grid[i][j]);
                }
            }
        }
        forKill.forEach(Node::kill);
        forLive.forEach(Node::alive);
//        if(compareGrids(grid,copyOfGrid))
//            checkInRow++;
//        else
//            checkInRow=0;
//        if(checkInRow>=10)
//            throw new Exception("game ends");
    }

    private boolean compareGrids(Node[][] grid1,Node[][] grid2){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(!grid1[i][j].equals(grid2[i][j])){
                    return false;
                }
            }
        }
        return true;
    }

    private int checkIfMiddle(int i,int j){
        int aliveNeighbours=0;
        if(j>0&&j<size-1){//если не на краях строки
            List<Node> list=Arrays.asList(grid[i-1][j-1],
                    grid[i-1][j],
                    grid[i-1][j+1],
                    grid[i][j-1],
                    grid[i][j+1],
                    grid[i+1][j-1],
                    grid[i+1][j],
                    grid[i+1][j+1]);
            aliveNeighbours+=list.stream().filter(Node::isAlive).count();
        }
        else if(j==0){//если левый край строки
            List<Node> list=Arrays.asList(grid[i-1][j],
                    grid[i-1][j+1],
                    grid[i][j+1],
                    grid[i+1][j],
                    grid[i+1][j+1]);
            aliveNeighbours+=list.stream().filter(Node::isAlive).count();
        }
        else{//если правый край строки
            List<Node> list=Arrays.asList(grid[i-1][j-1],
                    grid[i-1][j],
                    grid[i][j-1],
                    grid[i+1][j-1],
                    grid[i+1][j]);
            aliveNeighbours+=list.stream().filter(Node::isAlive).count();
        }
        return aliveNeighbours;
    }
    private int checkIfTop(int i,int j){
        int aliveNeighbours=0;
        if(j>0&&j<size-1){//если не на краях строки
            List<Node> list=Arrays.asList(grid[i+1][j-1],
                    grid[i+1][j],
                    grid[i+1][j+1],
                    grid[i][j-1],
                    grid[i][j+1]);
            aliveNeighbours+=list.stream().filter(Node::isAlive).count();
        }
        else if(j==0){//если левый край строки
            List<Node> list=Arrays.asList(grid[i+1][j],
                    grid[i+1][j+1],
                    grid[i][j+1]);
            aliveNeighbours+=list.stream().filter(Node::isAlive).count();
        }
        else{//если правый край строки
            List<Node> list=Arrays.asList(grid[i+1][j-1],
                    grid[i+1][j],
                    grid[i][j-1]);
            aliveNeighbours+=list.stream().filter(Node::isAlive).count();
        }
        return aliveNeighbours;
    }
    private int checkIfBottom(int i,int j){
        int aliveNeighbours=0;
        if(j>0&&j<size-1){//если не на краях строки
            List<Node> list=Arrays.asList(grid[i-1][j-1],
                    grid[i-1][j],
                    grid[i-1][j+1],
                    grid[i][j-1],
                    grid[i][j+1]);
            aliveNeighbours+=list.stream().filter(Node::isAlive).count();
        }
        else if(j==0){//если левый край строки
            List<Node> list=Arrays.asList(grid[i-1][j],
                    grid[i-1][j+1],
                    grid[i][j+1]);
            aliveNeighbours+=list.stream().filter(Node::isAlive).count();
        }
        else{//если правый край строки
            List<Node> list=Arrays.asList(grid[i-1][j-1],
                    grid[i-1][j],
                    grid[i][j-1]);
            aliveNeighbours+=list.stream().filter(Node::isAlive).count();
        }
        return aliveNeighbours;
    }

    public void print(){
        for(Node[] nodes:this.grid){
            for(Node node:nodes){
                System.out.print(node.getRepresent());
            }
            System.out.println();
        }
    }

}
