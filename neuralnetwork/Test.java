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
                   // System.out.println(profchi.neurons[0].getWeights()[0]);
                    //System.out.println(profchi.neurons[1].getWeights()[0]);
                    //System.out.println(profchi.neurons[2].getWeights()[0]);
                   // System.out.println(profchi.neurons[3].getWeights()[2]);
                    //System.out.println(profchi.neurons[4].getWeights()[2]);
                    //System.out.println(profchi.neurons[5].getWeights()[2]);
                    //System.out.println();
                    //System.out.println(profchi.neurons[7].getWeights()[1]);
                    //System.out.println(profchi.neurons[7].getErrorSignal());
                    //System.out.println(profchi.neurons[7].getOutput());

                }
                //System.out.println();
                //System.out.println(profchi.neurons[7].getOutput());
                if (errorTotal < 0.05){
                   System.out.println(epochs);
                   break;
                }
            }
        }/*
        profchi.feedInputs(xor_input, 3);
        System.out.println(profchi.neurons[7].getOutput());
        System.out.println(profchi.neurons[7].getErrorSignal());
        double [] me = {1};
        //profchi.neurons[7].updateErrorSignal(0, me);
        System.out.println(profchi.neurons[7].getErrorSignal());
        System.out.println(profchi.neurons[7].getWeights()[2]);
        System.out.println(profchi.neurons[5].getOutput());
        System.out.println(profchi.neurons[5].getErrorSignal());
        profchi.backwardPropagation(me);
        //profchi.neurons[5].updateErrorSignal(2, xor_output);
        //System.out.println(Neuron.Derivative(profchi.neurons[5], profchi.neurons[5].getOutput()));
        System.out.println(profchi.neurons[7].getErrorSignal());
        System.out.println(profchi.neurons[5].getErrorSignal());
        /*double [] test = profchi.neurons[11].getInputs();
        for (int i = 0; i < test.length; ++i){
            System.out.println(test[i]); 
        }
        System.out.println(profchi.neurons[11].getOutput());
        System.out.println(profchi.neurons[6].outputNeuron.get(1).getOutput());
        System.out.println(profchi.neurons[7].numberOfOutputNeurons());
        System.out.println(profchi.neurons[8].getOutput());
        System.out.println(profchi.neurons[9].getOutput());
        System.out.println(profchi.neurons[0].getOutput());
        System.out.println(profchi.neurons[5].inputNeuron.get(2).getOutput());
        */
    }
    
}
