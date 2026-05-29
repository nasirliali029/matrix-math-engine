public class DNAAlignment{
    public static void main(String[] args){
        String seq1="GATTACA";
        String seq2="GCATAC";
        System.out.println("Sequence 1: "+seq1);
        System.out.println("Sequence 2: "+seq2+"\n");
        if(isValidDNA(seq1)&&isValidDNA(seq2)){
            alignSequences(seq1,seq2);
        }
        else{
            System.out.println("Error: Invalid DNA sequences. Strings must only contain A, C, G, T.");
        }
    }
    public static boolean isValidDNA(String seq){
        if(seq==null||seq.isEmpty()){
            return false;
        }
        for(char c:seq.toUpperCase().toCharArray()){
            if(c!='A'&&c!='C'&&c!='G'&&c!='T'){
                return false;
            }
        }
        return true;
    }
    public static void alignSequences(String s1,String s2){
        int n=s1.length();
        int m=s2.length();
        final int MATCH=1;
        final int MISMATCH=-1;
        final int GAP=-1;
        int[][] scoreMatrix=new int[n+1][m+1];
        for(int i=0;i<=n;i++){
            scoreMatrix[i][0]=i*GAP;
        }
        for(int j=0;j<=m;j++){
            scoreMatrix[0][j]=j*GAP;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                int matchScore=(s1.charAt(i-1)==s2.charAt(j-1))?MATCH:MISMATCH;
                int diagonal=scoreMatrix[i-1][j-1]+matchScore;
                int up=scoreMatrix[i-1][j]+GAP;
                int left=scoreMatrix[i][j-1]+GAP;
                scoreMatrix[i][j]=Math.max(diagonal,Math.max(up,left));
            }
        }
        StringBuilder aligned1=new StringBuilder();
        StringBuilder aligned2=new StringBuilder();
        int i=n;
        int j=m;
        while(i>0||j>0){
            if(i>0&&j>0&&(scoreMatrix[i][j]==scoreMatrix[i-1][j-1]+((s1.charAt(i-1)==s2.charAt(j-1))?MATCH:MISMATCH))){
                aligned1.append(s1.charAt(i-1));
                aligned2.append(s2.charAt(j-1));
                i--;
                j--;
            }
            else if(i>0&&(j==0||scoreMatrix[i][j]==scoreMatrix[i-1][j]+GAP)){
                aligned1.append(s1.charAt(i-1));
                aligned2.append('-');
                i--;
            }
            else{
                aligned1.append('-');
                aligned2.append(s2.charAt(j-1));
                j--;
            }
        }
        System.out.println("Optimal Global Alignment:");
        System.out.println(aligned1.reverse().toString());
        System.out.println(aligned2.reverse().toString());
        System.out.println("\nAlignment Score: "+scoreMatrix[n][m]);
    }
}
