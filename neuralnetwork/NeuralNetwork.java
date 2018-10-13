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
public class NeuralNetwork {
   /* private List<Layer> Neuralnet;
    public NeuralNetwork (){
        Neuralnet = new ArrayList<Layer>();
    }
    public void addLayer(Layer layer){
        Neuralnet.add(layer);
    }*/
    public Layer [] neuralNet;
    public Neuron [] neurons;
    
    public void initialiseNetwork(){
        int numberOfInputs;
        for(int i = 1; i < neuralNet.length; ++i){
            numberOfInputs = neuralNet[i-1].layerSize();
            for(int j = 0; j < neuralNet[i].layerSize(); ++j){
                double [] inputs = new double [numberOfInputs + 1];
                double [] weights = new double [numberOfInputs + 1];
                for(int k = 0; k < numberOfInputs;++k){
                    neuralNet[i].getNeuron(j).addInputNeuron(neuralNet[i-1].getNeuron(k)); 
                    //weights[k] =  0.1;
                    weights[k] = Math.random()- 0.5;
                }
                neuralNet[i].getNeuron(j).addInputNeuron(neurons[0]);
               //weights[numberOfInputs] = 0.1;
                weights[numberOfInputs] = Math.random() - 0.5;
                neuralNet[i].getNeuron(j).setInputs(inputs);
                neuralNet[i].getNeuron(j).setWeights(weights);
                neuralNet[i].getNeuron(j).setPreviousWeights(weights);
                neuralNet[i].getNeuron(j).updateOutput(0);
            }
        }
        neurons[0].updateOutput(1);
    
    }
    
    public NeuralNetwork(){
    
    }
    
    public void createNeuralNetwork(int [] layout){
        int numberOfNeurons = 0;
        int numberOfLayers = layout.length;
        int iterateNeurons = 1;
        if(layout == null)
            return;
        else{
            neuralNet = new Layer[numberOfLayers];
            for(int i = 0; i < numberOfLayers; ++i){
                numberOfNeurons += layout[i];
                if(i == 0)
                    neuralNet[i] = new Layer ("input");
                else if(i == numberOfLayers - 1)
                    neuralNet[i] = new Layer ("output");
                else
                    neuralNet[i] = new Layer ("hidden");
            }
            neurons = new Neuron[numberOfNeurons + 1];
            neurons[0] = new Neuron("input");
            for(int i = 1; i <= layout[0]; ++i){
                neurons[iterateNeurons] = new Neuron("input");
                neuralNet[0].addNeuron(neurons[iterateNeurons]);
                ++iterateNeurons;
            }
            for(int i = 1; i < numberOfLayers - 1; ++i){
                for(int j = 0; j < layout[i]; ++j){
                    neurons[iterateNeurons] = new Neuron ("hidden");
                    neuralNet[i].addNeuron(neurons[iterateNeurons]);
                    ++iterateNeurons;
                }
            }
            while(iterateNeurons <= numberOfNeurons){
                neurons[iterateNeurons] = new Neuron ("output");
                neuralNet[numberOfLayers - 1].addNeuron(neurons[iterateNeurons]);
                ++iterateNeurons;
            }
        }
    }
    
   public void forwardPropagation(){
       for(int layerIndex = 1; layerIndex < neuralNet.length;++layerIndex){
           for(int neuronIndex = 0; neuronIndex < neuralNet[layerIndex].layerSize(); ++neuronIndex){
               neuralNet[layerIndex].getNeuron(neuronIndex).updateOutput(0);
           
           }
       }
   }
   
   public void backwardPropagation(double [] output){
       for(int layerIndex = neuralNet.length-1; layerIndex >= 1; --layerIndex){
           for(int neuronIndex = 0; neuronIndex < neuralNet[layerIndex].layerSize(); ++neuronIndex){
               neuralNet[layerIndex].getNeuron(neuronIndex).updateErrorSignal(neuronIndex, output);
               neuralNet[layerIndex].getNeuron(neuronIndex).updateWeights();
           }
       }
   }
   
   public void feedInputs(double [][] inputs, int row ){
        for(int i = 0; i < inputs[row].length ; ++i){ 
            neuralNet[0].getNeuron(i).updateOutput(inputs[row][i]);
        }
    }
    /**
     * @param args the command line arguments
     */
    public double [] extractOutput(){
        double [] OutputFromNetwork = new double[neuralNet[neuralNet.length-1].layerSize()];
        for(int i = 0; i < OutputFromNetwork.length ; ++i){
            OutputFromNetwork[i] = neuralNet[neuralNet.length-1].getNeuron(i).getOutput();
        }
        return OutputFromNetwork;
    }
}
