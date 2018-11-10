/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fog.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ANNetwork
{
    public static final double defaultLearningRate = 0.4;
    public static final double defaultMomentum = 0.8;

    private NLayer inputLayer;
    private ArrayList<NLayer> hiddenLayers;
    private NLayer outputLayer;

    private ArrayList<NLayer> layers;

    private double momentum = NNetwork1.defaultMomentum;    // alpha: momentum, default! 0.3

    private ArrayList<Double> learningRates;

    public ANNetwork (int nInputs, int nOutputs, Integer... neuronsPerHiddenLayer)
    {
        this(nInputs, nOutputs, Arrays.asList(neuronsPerHiddenLayer));
    }

    public ANNetwork (int nInputs, int nOutputs, List<Integer> neuronsPerHiddenLayer)
    {
        // the number of neurons on the last layer build so far (i.e. the number of inputs for each neuron of the next layer)
        int prvOuts = 1;

        this.layers = new ArrayList<>();

        // input layer
        this.inputLayer = new NLayer(nInputs, prvOuts, this);
        this.inputLayer.setAllWeightsTo(1.0);
        this.inputLayer.setAllBiasesTo(0.0);
        this.inputLayer.useSigmaForOutput(false);
        prvOuts = nInputs;
        this.layers.add(this.inputLayer);

        // hidden layers
        this.hiddenLayers = new ArrayList<>();
        for (int i=0 ; i<neuronsPerHiddenLayer.size() ; i++)
        {
            this.hiddenLayers.add(new NLayer(neuronsPerHiddenLayer.get(i), prvOuts, this));
            prvOuts = neuronsPerHiddenLayer.get(i);
        }
        this.layers.addAll(this.hiddenLayers);

        // output layer
        this.outputLayer = new NLayer(nOutputs, prvOuts, this);
        this.layers.add(this.outputLayer);

        this.initCoeffs();
    }

    private void initCoeffs ()
    {
        this.learningRates = new ArrayList<>();
        // learning rates of the hidden layers
        for (int i=0 ; i<this.hiddenLayers.size(); i++)
            this.learningRates.add(NNetwork1.defaultLearningRate);

        // learning rate of the output layer
        this.learningRates.add(NNetwork1.defaultLearningRate);
    }

    public double getLearningRate (int layerIndex)
    {
        if (layerIndex > 0 && layerIndex <= this.hiddenLayers.size()+1)
        {
            return this.learningRates.get(layerIndex-1);
        }
        else
        {
            return 0;
        }
    }

    public ArrayList<Double> getLearningRates ()
    {
        return this.learningRates;
    }

    public void setLearningRate (int layerIndex, double newLearningRate)
    {
        if (layerIndex > 0 && layerIndex <= this.hiddenLayers.size()+1)
        {
            this.learningRates.set(
                    layerIndex-1,
                    newLearningRate);
        }
    }

    public void setLearningRates (Double... newLearningRates)
    {
        this.setLearningRates(Arrays.asList(newLearningRates));
    }

    public void setLearningRates (List<Double> newLearningRates)
    {
        int len = (this.learningRates.size() <= newLearningRates.size())
                ? this.learningRates.size()
                : newLearningRates.size();

        for (int i=0; i<len; i++)
            this.learningRates
                    .set(i,
                    newLearningRates.get(i));
    }

    public double getMomentum ()
    {
        return this.momentum;
    }

    public void setMomentum (double momentum)
    {
        this.momentum = momentum;
    }

    public void getNeuron (int layerIndex, int neuronIndex)
    {
        if (layerIndex == 0) { //return this.inputLayer.getNeurons().get(neuronIndex);
        //else if (layerIndex == this.hiddenLayers.size()+1)
        ///return this.outputLayer.getNeurons().get(neuronIndex);
        //else
        }
    }

    public ArrayList<Double> getOutput (ArrayList<Double> inputs)
    {
        ArrayList<Double> lastOuts = inputs;    // the last computed outputs of the last 'called' layer so far

        // input layer
        //lastOuts = this.inputLayer.getOutput(lastOuts);
       // lastOuts = this.getInputLayerOutputs(lastOuts);

        // hidden layers
        for (Iterator<NLayer> it = this.hiddenLayers.iterator(); it.hasNext();) {
            NLayer layer = it.next();
            lastOuts = layer.getOutput(lastOuts);
        }

        // output layer
        lastOuts = this.outputLayer.getOutput(lastOuts);

        return lastOuts;
    }

    public ArrayList<ArrayList<Double>> getAllOutputs (ArrayList<Double> inputs)
    {
        ArrayList<ArrayList<Double>> outs = new ArrayList<>();

        // input layer
      //  outs.add(this.getInputLayerOutputs(inputs));

        // hidden layers
        for (NLayer layer : this.hiddenLayers)
            outs.add(layer.getOutput(outs.get(outs.size()-1)));

        // output layer
        outs.add(this.outputLayer.getOutput(outs.get(outs.size()-1)));

        return outs;
    }

    public ArrayList<ArrayList<Double>> getAllSums (ArrayList<Double> inputs)
    {
        //*
        ArrayList<ArrayList<Double>> sums = new ArrayList<>();
        ArrayList<Double> lastOut = null;

        // input layer
        sums.add(inputs);
       // lastOut = this.getInputLayerOutputs(inputs);

        // hidden nodes
        for (NLayer layer : this.hiddenLayers)
        {
            sums.add(layer.getSums(lastOut));

            lastOut = layer.getOutput(lastOut);
        }

        // output layer
        sums.add(this.outputLayer.getSums(lastOut));

        return sums;
    }

    public void getInputLayerOutputs (ArrayList<Double> inputs)
    {
        ArrayList<Double> outs = new ArrayList<>();
        int i = 0;
     //   for (int i=0 ; i<this.inputLayer.getNeurons().size() ; i++)
           // outs.add((Double) this
      //              .inputLayer
   //                 .getNeuron(i)return outs;
    }

    public void changeWeights (
            ArrayList<ArrayList<Double>> deltaW,
            ArrayList<ArrayList<Double>> inputSet,
            ArrayList<ArrayList<Double>> targetSet,
            boolean checkError)
    {
        for (int i=0 ; i<deltaW.size()-1 ; i++)
            this.hiddenLayers.get(i).changeWeights(deltaW.get(i), inputSet, targetSet, checkError);

        this.outputLayer.changeWeights(deltaW.get(deltaW.size()-1), inputSet, targetSet, checkError);

    }

    public int train2 (
            ArrayList<ArrayList<Double>> inputSet,
            ArrayList<ArrayList<Double>> targetSet,
            double maxError,
            int maxIterations)
    {
        ArrayList<Double>
                input,
                target;

        ArrayList<ArrayList<ArrayList<Double>>> prvNetworkDeltaW = null;

        double error;

        int i = 0, j = 0, traininSetLength = inputSet.size();
        do  // during each itreration...
        {
            error  = 0.0;
            for (j = 0; j < traininSetLength; j++)  // ... for each training element...
            {
                input = inputSet.get(j);
                target = targetSet.get(j);
                prvNetworkDeltaW = this.train2_bp(input, target, prvNetworkDeltaW); // ... do backpropagation, and return the new weight deltas

                error += this.getInputMeanSquareError(input, target);
            }

            i++;
        } while (error > maxError && i < maxIterations);    // iterate as much as necessary/possible

        return i;
    }

    public ArrayList<ArrayList<ArrayList<Double>>> train2_bp (
            ArrayList<Double> input,
            ArrayList<Double> target,
            ArrayList<ArrayList<ArrayList<Double>>> prvNetworkDeltaW)
    {
        ArrayList<ArrayList<Double>> layerSums = this.getAllSums(input);        // the sums for each layer
        ArrayList<ArrayList<Double>> layerOutputs = this.getAllOutputs(input);  // the outputs of each layer

        // get the layer deltas (inc the input layer that is null)
        ArrayList<ArrayList<Double>> layerDeltas = this.train2_getLayerDeltas(layerSums, layerOutputs, target);

        // get the weight deltas
        ArrayList<ArrayList<ArrayList<Double>>> networkDeltaW = this.train2_getWeightDeltas(layerOutputs, layerDeltas, prvNetworkDeltaW);

        // change the weights
        this.train2_updateWeights(networkDeltaW);

        return networkDeltaW;
    }

    public void train2_updateWeights (ArrayList<ArrayList<ArrayList<Double>>> networkDeltaW)
    {
        for (int i=1; i<this.layers.size(); i++)
            this.layers.get(i).train2_updateWeights(networkDeltaW.get(i));
    }

    public ArrayList<ArrayList<ArrayList<Double>>> train2_getWeightDeltas (
            ArrayList<ArrayList<Double>>            layerOutputs,
            ArrayList<ArrayList<Double>>            layerDeltas,
            ArrayList<ArrayList<ArrayList<Double>>> prvNetworkDeltaW)
    {
        ArrayList<ArrayList<ArrayList<Double>>> networkDeltaW = new ArrayList<>(this.layers.size());
                ArrayList<ArrayList<Double>>  layerDeltaW;
                            ArrayList<Double>   neuronDeltaW;

        for (int i=0; i<this.layers.size(); i++)
            networkDeltaW.add(new ArrayList<ArrayList<Double>>());

        double
                deltaW, x, learningRate, prvDeltaW, d;

        int i, j = 0, k = 0;
        for (i=this.layers.size()-1; i>0; i--)  // for each layer
        {
            learningRate = this.getLearningRate(i);

            layerDeltaW = new ArrayList<>();
            networkDeltaW.set(i, layerDeltaW);

            //for (j=0; j<this.layers.get(i).getNeurons().size(); j++)    // for each neuron of this layer
            {
                neuronDeltaW = new ArrayList<>();
                layerDeltaW.add(neuronDeltaW);

        //        for (k=0; k<this.layers.get(i-1).getNeurons().size(); k++)  // for each weight (i.e. each neuron of the previous layer)
                {
                    d = layerDeltas.get(i).get(j);
                    x = layerOutputs.get(i-1).get(k);
                    prvDeltaW = (prvNetworkDeltaW != null)
                            ? prvNetworkDeltaW.get(i).get(j).get(k)
                            : 0.0;

                    deltaW = -learningRate * d * x + this.momentum * prvDeltaW;

                    neuronDeltaW.add(deltaW);
                }

                // the bias !!
                d = layerDeltas.get(i).get(j);
                x = 1;
                prvDeltaW = (prvNetworkDeltaW != null)
                        ? prvNetworkDeltaW.get(i).get(j).get(prvNetworkDeltaW.get(i).get(j).size()-1)
                        : 0.0;

                deltaW = -learningRate * d * x + this.momentum * prvDeltaW;

                neuronDeltaW.add(deltaW);
            }
        }

        return networkDeltaW;
    }

    ArrayList<ArrayList<Double>> train2_getLayerDeltas (
            ArrayList<ArrayList<Double>>    layerSums,
            ArrayList<ArrayList<Double>>    layerOutputs,
            ArrayList<Double>               target)
    {
        // get ouput deltas
        ArrayList<Double> outputDeltas = new ArrayList<>(); // the output layer deltas
        double
                oErr,   // output error given a target
                s,  // sum
                o,  // output
                d = 0;  // delta
        int
                nOutputs = target.size(),   // @TODO ?== this.outputLayer.size()
                nLayers = this.hiddenLayers.size()+2;   // @TODO ?== layerOutputs.size()

        for (int i=0; i<nOutputs; i++)  // for each neuron...
        {
            s = layerSums.get(nLayers-1).get(i);
            o = layerOutputs.get(nLayers-1).get(i);
            oErr = (target.get(i) - o);
     //       d = -oErr * this.getNeuron(nLayers-1, i).sigmaPrime(s); // @TODO "s" or "o" ??

            outputDeltas.add(d);
        }

        // get hidden deltas
        ArrayList<ArrayList<Double>> hiddenDeltas = new ArrayList<>();
        for (int i=0; i<this.hiddenLayers.size(); i++)
            hiddenDeltas.add(new ArrayList<Double>());

        NLayer nextLayer = this.outputLayer;
        ArrayList<Double> nextDeltas = outputDeltas;

        int
                h, k = 0;
              //  nHidden = this.hiddenLayers.size(),
      //          nNeurons = this.hiddenLayers.get(nHidden-1).getNeurons().size();
        double
                wdSum = 0.0;
        int nHidden = 0;
        for (int i=nHidden-1; i>=0; i--)    // for each hidden layer
        {
            hiddenDeltas.set(i, new ArrayList<Double>());
            int nNeurons = 0;
            for (h=0; h<nNeurons; h++)
            {
                wdSum = 0.0;
           //     for (k=0; k<nextLayer.getNeurons().size(); k++)
                {
                //    wdSum += nextLayer.getNeuron(k).getWeight(h) * nextDeltas.get(k);
                }

                s = layerSums.get(i+1).get(h);
         //       d = this.getNeuron(i+1, h).sigmaPrime(s) * wdSum;

                hiddenDeltas.get(i).add(d);
            }

            nextLayer = this.hiddenLayers.get(i);
            nextDeltas = hiddenDeltas.get(i);
        }

        ArrayList<ArrayList<Double>> deltas = new ArrayList<>();

        // input layer deltas: void
        deltas.add(null);

        // hidden layers deltas
        deltas.addAll(hiddenDeltas);

        // output layer deltas
        deltas.add(outputDeltas);

        return deltas;
    }

    public double getInputMeanSquareError (ArrayList<Double> input, ArrayList<Double> target)
    {
        double diff, mse=0.0;
        ArrayList<Double> output = this.getOutput(input);
        for (int i=0; i<target.size(); i++)
        {
            diff = target.get(i) - output.get(i);
            mse += (diff * diff);
        }

        mse /= 2.0;

        return mse;
    }

    private static class NLayer {

        public NLayer() {
        }

        private NLayer(int nInputs, int prvOuts, ANNetwork aThis) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void setAllWeightsTo(double d) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void setAllBiasesTo(double d) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void useSigmaForOutput(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private Object getNeurons() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private ArrayList<Double> getOutput(ArrayList<Double> lastOuts) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private ArrayList<Double> getSums(ArrayList<Double> lastOut) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private Object getNeuron(int i) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void changeWeights(ArrayList<Double> get, ArrayList<ArrayList<Double>> inputSet, ArrayList<ArrayList<Double>> targetSet, boolean checkError) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void train2_updateWeights(ArrayList<ArrayList<Double>> get) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private static class NNetwork1 {

        private static double defaultMomentum;
        private static Double defaultLearningRate;

        public NNetwork1() {
        }
    }

    private static class NNeuron {

        public NNeuron() {
        }

        private double sigmaPrime(double s) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}