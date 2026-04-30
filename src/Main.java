public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                "informe dois arquivos de entrada. Ex.: java Main ../dados/arvore1.txt ../dados/arvore2.txt"
            );
        }

        Graph tree1 = new Graph(new In(args[0]));
        Graph tree2 = new Graph(new In(args[1]));

        StdOut.println("Arvore 1:");
        StdOut.println(tree1);
        StdOut.println();

        StdOut.println("Arvore 2:");
        StdOut.println(tree2);
        StdOut.println();

        TreeIsomorphism analysis1 = new TreeIsomorphism(tree1);
        TreeIsomorphism analysis2 = new TreeIsomorphism(tree2);

        StdOut.println("Validacao arvore 1:");
        StdOut.println(analysis1.getValidationMessage());
        StdOut.println();

        StdOut.println("Validacao arvore 2:");
        StdOut.println(analysis2.getValidationMessage());
        StdOut.println();

        if (!analysis1.isTree() || !analysis2.isTree()) {
            StdOut.println("Nao e possivel comparar: uma das entradas nao representa uma arvore.");
            return;
        }

        StdOut.print("Centro(s) arvore 1: ");
        printArray(analysis1.getCenters());
        StdOut.println();

        StdOut.print("Centro(s) arvore 2: ");
        printArray(analysis2.getCenters());
        StdOut.println();
        StdOut.println();

        String code1 = analysis1.getCanonicalEncoding();
        String code2 = analysis2.getCanonicalEncoding();

        StdOut.println("Codificacao canonica arvore 1:");
        StdOut.println(code1);
        StdOut.println();

        StdOut.println("Codificacao canonica arvore 2:");
        StdOut.println(code2);
        StdOut.println();

        if (code1.equals(code2)) {
            StdOut.println("Resultado: As arvores sao isomorfas.");
        } else {
            StdOut.println("Resultado: As arvores nao sao isomorfas.");
        }
    }

    private static void printArray(int[] arr) {
        StdOut.print("[");
        for (int i = 0; i < arr.length; i++) {
            StdOut.print(arr[i]);
            if (i < arr.length - 1) {
                StdOut.print(", ");
            }
        }
        StdOut.print("]");
    }
}
