/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

//import static neuralnetwork.NeuralNetwork.neuralNet;
//import static neuralnetwork.NeuralNetwork.neurons;

/**
 *
 * @author CHINEDU PC
 */
public class Test {
    
    public static void main(String[] args) {
   
        int [] myArray = {2,4,1};
        NeuralNetwork profchi = new NeuralNetwork();
        double [][] xor_input = {{-1,-1},{-1,1},{1,-1},{1,1}};
        double [] xor_output = {-1,1,1,-1};
        profchi.createNeuralNetwork(myArray);
       // profchi.neuralNet[0].getNeuron(1).updateOutput(0.15f);
        profchi.initialiseNetwork();
        int inputTotal;
        double [] outNeurons;
        double errorTotal;
        for(int start = 0; start < 10; ++start){
            profchi.createNeuralNetwork(myArray);
            profchi.initialiseNetwork();
            for(int epochs = 1;; ++epochs){
                errorTotal = 0;
                for(int inputIndex = 0; inputIndex < 4; ++inputIndex){
                    profchi.feedInputs(xor_input,inputIndex);
                    profchi.forwardPropagation();
                    double [] me = {xor_output[inputIndex]};
                    profchi.backwardPropagation(me);

                    outNeurons = profchi.extractOutput();
                    for(int i = 0; i < outNeurons.length; ++i){
                        errorTotal += Math.pow((outNeurons[i]- xor_output[inputIndex]),2)/ 2;
                    }
                  

                }
                if (errorTotal < 0.05){
                   System.out.println(epochs);
                   break;
                }
            }
        }
    }
    
}
