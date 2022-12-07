// TC : O(k ^ n)
// SC : O(N)
class Solution {
    List<String> result;
    public String[] expand(String s) {
        if (s == null || s.length() == 0) return new String[]{};

        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;
        result = new ArrayList<>();


        while (i < s.length()) {
            char c = s.charAt(i);
            List<Character> b = new ArrayList<>();
            if (c == '{') {
                i++;
                while(s.charAt(i)!='}') {
                    if (s.charAt(i) != ',') {
                        b.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                b.add(c);
            }
            Collections.sort(b);
            blocks.add(b);
            i++;
        }

        backtrack(blocks, 0, new StringBuilder());

        String[] answer = new String[result.size()];
        for(int j = 0; j < result.size(); j++) {
            answer[j] = result.get(j);
        }

        return answer;
    }

    private void backtrack(List<List<Character>> blocks, int index, StringBuilder path) {
        // base case
        if (index == blocks.size()) {
            result.add(path.toString());
            return;
        }


        //logic
        List<Character> block = blocks.get(index);
        for (int i= 0; i < block.size(); i++) {
            //action
            char c = block.get(i);
            path.append(c);

            //recurse
            backtrack(blocks, index+1, path);

            //backtrack
            path.setLength(path.length() - 1);
        }

    }
}