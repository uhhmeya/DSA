//The following code is authored by Ameya Mandhare
//The following code references chatgpt.com
import java.io.*;
import java.util.*;

public class HuffmanSubmit implements Huffman {

    private class Node implements Comparable<Node> {
        char x;
        int f;
        Node l, r;

        Node(char x, int f, Node l, Node r) {
            this.x = x;
            this.f = f;
            this.l = l;
            this.r = r;
        }

        public int compareTo(Node other) {
            return this.f - other.f;
        }

        boolean isLeaf() {
            return l == null && r == null;
        }
    }

    private Map<Character, String> ctc = new HashMap<>();

    public void encode(String inputFile, String outputFile, String freqFile) {
        try {
            Map<Character, Integer> fm = new HashMap<>();
            try (FileReader rd = new FileReader(inputFile)) {
                int x;
                while ((x = rd.read()) != -1) {
                    fm.put((char) x, fm.getOrDefault((char) x, 0) + 1);
                }
            }

            Node r = buildHuffmanTree(fm);

            generateCodes(r, "");

            try (BufferedWriter fw = new BufferedWriter(new FileWriter(freqFile))) {
                for (Map.Entry<Character, Integer> entry : fm.entrySet()) {
                    fw.write(String.format("%s:%d\n", String.format("%8s", Integer.toBinaryString(entry.getKey())).replace(' ', '0'), entry.getValue()));
                }
            }

            FileReader rd = new FileReader(inputFile);
            BinaryOut binaryO = new BinaryOut(outputFile);
            try {
                int x;
                while ((x = rd.read()) != -1) {
                    String cd = ctc.get((char) x);
                    for (char bit : cd.toCharArray()) {
                        binaryO.write(bit == '1');
                    }
                }
                binaryO.flush();
            } finally {
                rd.close();
                binaryO.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decode(String inputFile, String outputFile, String freqFile) {
        try {
            Map<Character, Integer> fm = new HashMap<>();
            try (BufferedReader fr = new BufferedReader(new FileReader(freqFile))) {
                String ln;
                while ((ln = fr.readLine()) != null) {
                    String[] parts = ln.split(":");
                    char x = (char) Integer.parseInt(parts[0], 2);
                    int f = Integer.parseInt(parts[1]);
                    fm.put(x, f);
                }
            }

            Node r = buildHuffmanTree(fm);

            BinaryIn binaryI = new BinaryIn(inputFile);
            BufferedWriter wr = new BufferedWriter(new FileWriter(outputFile));
            try {
                Node cur = r;
                while (!binaryI.isEmpty()) {
                    boolean bit = binaryI.readBoolean();
                    cur = bit ? cur.r : cur.l;

                    if (cur.isLeaf()) {
                        wr.write(cur.x);
                        cur = r;
                    }
                }
                wr.newLine(); // Ensure the decoded file ends with a newline
            } finally {
                wr.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Node buildHuffmanTree(Map<Character, Integer> fm) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : fm.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue(), null, null));
        }

        while (pq.size() > 1) {
            Node l = pq.poll();
            Node r = pq.poll();
            Node parent = new Node('\0', l.f + r.f, l, r);
            pq.add(parent);
        }
        return pq.poll();
    }

    private void generateCodes(Node r, String cd) {
        if (r.isLeaf()) {
            ctc.put(r.x, cd);
            return;
        }
        generateCodes(r.l, cd + '0');
        generateCodes(r.r, cd + '1');
    }

    public static void main(String[] args) {
        HuffmanSubmit huffman = new HuffmanSubmit();
        String inputFile = "alice30.txt";
        String outputFile = "alice30.enc";
        String freqFile = "freq.txt";
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        huffman.encode(inputFile, outputFile, freqFile);
        huffman.decode(outputFile, "alice30_decoded.txt", freqFile);
    }
}
