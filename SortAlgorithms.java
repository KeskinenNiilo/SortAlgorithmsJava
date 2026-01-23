
package com.mycompan.sortalgorithms;
import java.util.*;
public class SortAlgorithms {
    public class FunctionRef {
        
        public interface FunctionPointer {
            void methodSignature(int[] data);
        }
        
        public static void swap(int[] data, int a, int b) { // apufunctio että funktiot pysyy siisteinä
            int temp = data[a];
            data[a] = data[b];
            data[b] = temp;
        }
        
        public static void printArray(int[] data) {
            for (int i = 0; i < data.length; ++i) {
                System.out.print(data[i] + " ");
            }
            System.out.print("\n");
        }

        public static int[] makeRandomArray(int size) {
            int[] data = new int[size]; // uusi annetun kokoinen lista
            for (int i = 0; i < size; ++i) { 
                data[i] = gen.nextInt(); // lista[index] = uusi random integeri
            }
            return data;
        }
        
        public static int[] makeAscendingArray(int size) {
            int[] data = new int[size];
            for (int i = 0; i < size; ++i) {
                data[i] = i;
            }
            return data;
        }
        
        public static int[] makeDescendingArray(int size) {
            int[] data = new int[size];
            for (int i = 0; i < size; ++i) {
                data[i] = size - i;
            }
            return data;
        }

        public static void insertionSort(int[] data, int left, int right) {
            for (int i = left + 1; i <= right; ++i) { 
                int key = data[i];
                int j = i - 1;
                while (j >= left && data[j] > key) {
                    data[j + 1] = data[j];
                    j = j - 1;
                }
                data[j + 1] = key;
            }
        }

        public static void bubbleSort(int[] data) {
            for (int i = 0; i < data.length - 1; ++i) { // ulompi forloop käy elementit toiseksi viimeisimpään asti
                boolean swapped = false; // vaihdettu elementtien paikkoja?
                for (int j = 0; j < data.length - i - 1; ++j) {
                    if (data[j] > data[j + 1]) { // elementin vaihto
                        int temp = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                        swapped = true; // elementti vaihdettu, jatkaa forlooppia
                    }
                }
                if (!swapped) return; // jos mittän ei vaihdettu, lopettaa
            }
        }
        
        public static void shellSort(int[] data) {
            for (int gap = data.length / 2; gap > 0; gap /= 2) { // verrattavien alkoiden väli
                for (int i = gap; i < data.length; ++i) {
                    int temp = data[i]; // talteen nykyinen alkio
                    int j = i; // talteen nykyinen indeksi
                    while (j >= gap && data[j - gap] > temp) { // käydään lista läpi annetulla välillä
                        data[j] = data[j - gap];
                        j -= gap;
                    }
                    data[j] = temp; // talteen otettu arvo asetetaan saadulle j:n indeksille
                }
            }
        }
        
        public static void knuthSequenceShellSort(int[] data) {
            int g = 1;
            while (g < data.length / 3) g = g * 3 + 1; // maksimi numero knuthin sarjassa
            for (int gap = g; gap > 0; gap /= 3) {
                for (int i = 0; i < data.length; ++i) {
                    int temp = data[i];
                    int j = i;
                    while (j >= gap && data[j - gap] > temp) {
                        data[j] = data[j - gap];
                        j -= gap;
                    }
                    data[j] = temp;
                }
            }
        }
        
        public static void quickSort(int[] data, int low, int high) {
            if (low >= high) return;
            int pivot = data[high]; // arvo johon alkioita verrataan
            int index = low; // pivottia suuremman datan indeksi
            for (int i = low; i < high; ++i) {
                if (data[i] < pivot) { // jos data[i] pienenmpi kuin pivot vaihdetaan se pivottia suuremman alkion kanssa
                    int temp = data[index];
                    data[index] = data[i];
                    data[i] = temp;
                    ++index;
                }
            }
            int temp = data[index]; // vaihdetaan pivot oikealle paikalle
            data[index] = data[high];
            data[high] = temp;
            quickSort(data, low, index - 1); //recursio
            quickSort(data, index + 1, high);
        }
        
        private static final Random gen = new Random();
        
        public static void optimizedQuickSort(int[] data, int low, int high) {
            if (low >= high) return;
            int pivotIndex = gen.nextInt(low, high + 1); // vältetään huonoin O(n^2) tapaus jos lista laskeva tai nouseva
            int pivot = data[pivotIndex]; // optimoidaan valitsemalla random int pivotiksi
            int pTemp = data[pivotIndex];
            data[pivotIndex] = data[high];
            data[high] = pTemp;
            int index = low;
            for (int i = low; i < high; ++i) {
                if (data[i] < pivot) {
                    int temp = data[index];
                    data[index] = data[i];
                    data[i] = temp;
                    ++index;
                }
            }
            int temp = data[index];
            data[index] = data[high];
            data[high] = temp;
            optimizedQuickSort(data, low, index - 1);
            optimizedQuickSort(data, index + 1, high);
        }
        
        public static void merge(int[] data, int mid, int left, int right) {
            int[] L = new int[mid - left + 1]; // kopioidaan listat
            int[] R = new int[right - mid];
            for (int l = 0; l < L.length; ++l) L[l] = data[left + l];
            for (int r = 0; r < R.length; ++r) R[r] = data[mid + r + 1];          
            int l = 0; int r = 0; int i = left;
            while (l < L.length && r < R.length) { // syötetään data takaisin listaan suuruusjärjestyksessä
                if (L[l] <= R[r]) {
                    data[i] = L[l];
                    ++l;
                } else if (R[r] < L[l]) {
                    data[i] = R[r];
                    ++r;
                }
                ++i;
            }        
            while (l < L.length) { // syötetään yli jääneet
                data[i] = L[l];
                ++l; ++i;
            }
            while (r < R.length) {
                data[i] = R[r];
                ++r; ++i;
            }
        }
        
        public static void optimizedMerge(int[] data, int mid, int left, int right, int[] tempArray) {
            for (int k = left; k <= right; k++) tempArray[k] = data[k];
            int l = left; int r = mid + 1; int i = left;
            while (l <= mid && r <= right) {
                if (tempArray[l] <= tempArray[r]) {
                    data[i] = tempArray[l];
                    ++l;
                } else {
                    data[i] = tempArray[r];
                    ++r;
                }
                ++i;
            }
            while (l <= mid) {
                data[i] = tempArray[l];
                l++;
                i++;
            }
        }        
        
        public static void mergeSort(int[] data, int left, int right) {
            if (left < right) {
                int mid = left + (right - left) / 2; // indeksi keskeltä listaa
                mergeSort(data, left, mid); //recursio
                mergeSort(data, mid + 1, right); //yhdistetään
                merge(data, mid, left, right);
            }
        }
        
        public static void optimizedMergeSort(int[] data, int left, int right, int[] tempArray) {
            if (right - left < 16) {
                insertionSort(data, left, right);
                return;
            }
            int mid = left + (right - left) / 2;
            optimizedMergeSort(data, left, mid, tempArray);
            optimizedMergeSort(data, mid + 1, right, tempArray);
            optimizedMerge(data, mid, left, right, tempArray);
        }
        
        public static void javaArraysSort(int[] data) {Arrays.sort(data);}
        
        public static void measureExecSpeed(FunctionPointer method, String name, int size, int[] arr) {
            long start = System.nanoTime();
            method.methodSignature(arr);
            long taken = System.nanoTime() - start;
            System.out.println(name + ": " + String.format("%.3f", taken / 1000000.0) + "ms");
        }
    }
    

    public static void main(String[] args) {
        int size =250000;
        int[] tempArray = new int[size];
        FunctionRef.FunctionPointer[] sortMethods = new FunctionRef.FunctionPointer[9];
        sortMethods[0] = (data) -> FunctionRef.insertionSort(data, 0, data.length - 1);
        sortMethods[1] = FunctionRef::bubbleSort;
        sortMethods[2] = FunctionRef::shellSort;
        sortMethods[3] = FunctionRef::knuthSequenceShellSort;
        sortMethods[4] = (data) -> FunctionRef.quickSort(data, 0, data.length - 1);
        sortMethods[5] = (data) -> FunctionRef.optimizedQuickSort(data, 0, data.length - 1);
        sortMethods[6] = (data) -> FunctionRef.mergeSort(data, 0, data.length - 1);
        sortMethods[7] = (data) -> FunctionRef.optimizedMergeSort(data, 0, data.length - 1, tempArray);
        sortMethods[8] = FunctionRef::javaArraysSort;
        
        String[] names = {"Insertion Sort", "Bubble Sort", "Shell Sort", "Knuth Sequence Shell Sort",
            "Quick Sort", "Optimized Quick Sort", "Merge Sort", "Optimized Merge Sort", "Java Arrays.sort"};

        System.out.println("\nRandom: ");
        for (int i = 0; i < sortMethods.length; ++i) {
            int[] data = FunctionRef.makeRandomArray(size);
            FunctionRef.measureExecSpeed(sortMethods[i], names[i], size, data);
        }
        System.out.println("\nAscending: ");
        for (int i = 0; i < sortMethods.length; ++i) {
            int[] data = FunctionRef.makeAscendingArray(size);
            FunctionRef.measureExecSpeed(sortMethods[i], names[i], size, data);
        }
        System.out.println("\nDescending: ");
        for (int i = 0; i < sortMethods.length; ++i) {
            int[] data = FunctionRef.makeDescendingArray(size);
            FunctionRef.measureExecSpeed(sortMethods[i], names[i], size, data);
        }
    }
}
