public class Iter {

<<<<<<< HEAD
    public token getCurrent(int index, token tokens[]){
=======
    private int index;

    public token getCurrent(token tokens[], int index){
>>>>>>> 47140adcf84731a98019332f69f8849d3183740e
        if(tokens[0] == null){
            throw new IllegalArgumentException("List cannot be empty!");
        }
        else return tokens[index];
    }

    public token getLast(int index, token tokens[]){
        if(tokens[0] == null){
            throw new IllegalArgumentException("List cannot be empty!");
        }

        index--;

        if(tokens[index] == null){
            throw new IndexOutOfBoundsException("Index does not exist!");
        }
        else return tokens[index];
    }

    public token getNext(int index, token tokens[]){
        index++;

        if(tokens[index] == null){
            throw new IndexOutOfBoundsException("Index does not exist!");
        }

        return tokens[index];
    }
}
