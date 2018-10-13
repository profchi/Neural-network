/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;
import java.util.ArrayList;
import java.util.*;
/**
 *
 * @author CHINEDU PC
 */
public class Layer {
    
    private List<Neuron> layer;
    private String type;
    
    public Layer (String s) {
        layer = new ArrayList<Neuron>();
        type = s;
    }

    public void addNeuron(Neuron neuron){
        layer.add(neuron);
    }
    public int layerSize(){
        return layer.size();
    }
    public Neuron getNeuron(int i){
        return layer.get(i);
    }
}
