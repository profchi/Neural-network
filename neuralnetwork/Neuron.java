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
public class Neuron {
    private double [] inputs;
    private double output;
    private double [] weights;
    private double [] previousWeights;
    public List<Neuron> inputNeuron;
    public List<Neuron> outputNeuron;
    private String type;
    private double errorSignal;
    static double learningRate = 0.2;

    
    public Neuron(String s){
        inputNeuron = new ArrayList<Neuron>();
        outputNeuron = new ArrayList<Neuron>();
        type = s;
        output = 0;
        errorSignal = 0;
        
    }
     
       
    public double [] getInputs(){
        return this.inputs;  
    }
    public double [] getWeights(){
        return this.weights;  
    }
    public double  getOutput(){
        return this.output;  
    }
    public double  getErrorSignal(){
        return this.errorSignal;  
    }
    public void setInputs(double [] value){
        this.inputs = value;  
    }
    public void setWeights(double [] value){
        this.weights = value;  
    }
    public void setPreviousWeights(double [] value){
        this.previousWeights = value;  
    }
    
    public String  getType(){
        return this.type;  
    }
    
    public void addOutputNeuron(Neuron neuron){
        outputNeuron.add(neuron);
    } 
    public void addInputNeuron(Neuron neuron){
        inputNeuron.add(neuron);
        neuron.addOutputNeuron(this);
    }
    
    public int numberOfOutputNeurons(){
        return outputNeuron.size();
    }
    public void updateOutput(double value){
        if( type == "input")
            output = value;
        else{
            output = 0;
            for(int i = 0; i < inputNeuron.size(); ++i){
                inputs[i] = inputNeuron.get(i).getOutput();
                output += weights[i]*inputs[i];
            }
           
            //possible activation function code
            output = activationFunction(this, output);
         }
    } 
    public void updateErrorSignal(int neuronIndexOnLayer, double [] output){
        if(this.inputNeuron == null)
            return;
        double sumOfWeightedOutputNeuron = 0, result;
        if (getType() == "hidden"){
            for (int i = 0; i < this.outputNeuron.size(); ++i){
                sumOfWeightedOutputNeuron += outputNeuron.get(i).getErrorSignal()*outputNeuron.get(i).getWeights()[neuronIndexOnLayer];
            }
        }
        else if(getType() == "output")
            sumOfWeightedOutputNeuron = output[neuronIndexOnLayer] - getOutput();
        errorSignal = sumOfWeightedOutputNeuron * Derivative(this,getOutput());
        
    }
    
    public void updateWeights(){
        double [] weightDifference = new double [weights.length];
        for (int i = 0; i < weights.length; ++i){
            weightDifference[i] = weights[i] - previousWeights[i];
            previousWeights[i] = weights[i];
            weights[i] = weights[i] + weightDifference[i] + (learningRate*errorSignal*inputNeuron.get(i).getOutput());
        }
    }
    
     public static double activationFunction(Neuron neuron, double output){
        double result;
        result = (1 - Math.exp(-1*output))/(1 + Math.exp(-1*output));
        return result;
    }
    public static double Derivative(Neuron neuron, double output){
        double result;
        result =(1-(output*output))/2;
        return result;
    }
}
