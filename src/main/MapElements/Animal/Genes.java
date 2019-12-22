package MapElements.Animal;

import java.util.*;

public class Genes {
    private ArrayList<Integer> genome;
    //to avoid occurrence of magic numbers in code
    private static final int genesNumber = 8; //there are possible 8 different genes from 0 to 7
    private static final int genomeSize = 32; // genome size is 32 gene

    public Genes(){
        this.genome = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < genomeSize; i++){
            int x = random.nextInt(genesNumber);
            this.genome.add(x);
        }
        Collections.sort(this.genome);
    }

    public Genes(Genes g1, Genes g2){
        this.genome = new ArrayList<>();
        Random random = new Random();

        //to divide parents genomes into parts, which they will pass to child
        //indices must be from range 1-31, to assure that genomes are divided in three parts
        //leftIndex must be smaller than rightIndex
        int rightIndex = random.nextInt(genomeSize-1) + 1;

        int leftIndex = random.nextInt(rightIndex );

        for(int i = 0; i < leftIndex; i++){
            this.genome.add(g1.getGenome().get(i));
        }
        for(int i = leftIndex; i < rightIndex; i++){
            this.genome.add(g2.getGenome().get(i));
        }
        for(int i = rightIndex; i < genomeSize; i++){
            this.genome.add(g1.genome.get(i));
        }

        //check and repair genome if there is a lack of some gene
        validateGenome();
    }

    void validateGenome(){
        //for every gene check if it is in genome. If not choose random gene which occurs more than one time
        //and replace it with missing one.
        for(int i = 0; i < genesNumber; i++){
            while( !this.genome.contains(i) ){
                Random random = new Random();
                int geneToReplaceIndex = random.nextInt(genomeSize);
                Integer geneToReplace = this.genome.get(geneToReplaceIndex);
                //check if this gene occurs more than one time
                if( this.genome.lastIndexOf(geneToReplace) - this.genome.indexOf(geneToReplace) >= 1){
                   this.genome.set(this.genome.lastIndexOf(geneToReplace), i);
                }
            }
        }

        Collections.sort(this.genome);
    }

    public ArrayList<Integer> getGenome() {
        return this.genome;
    }


    int generateNumberOfTurns(){
        Random random = new Random();
        return this.genome.get(random.nextInt(32));
    }

    @Override
    public String toString(){
        return this.genome.toString();
    }


}
