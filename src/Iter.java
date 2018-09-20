public class Iter {

    public token getCurrent(int index, token tokens[]){
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
