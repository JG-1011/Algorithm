class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLength = timeToSeconds(video_len);
        int position = timeToSeconds(pos);
        int openingStart = timeToSeconds(op_start);
        int openingEnd = timeToSeconds(op_end);
        
        for(String command : commands){
            if(position >= openingStart && position <= openingEnd){
                position = openingEnd;
            }
            
            if(command.equals("prev")){
                position = Math.max(0,position-10);
            }
            
            else if (command.equals("next")) {
                position = Math.min(videoLength, position + 10);
            }
        }
        
        if(position >= openingStart && position <= openingEnd){
            position = openingEnd;
        }
        
        return secondsToTime(position);
    }
    private static int timeToSeconds(String time){
        String[] parts = time.split(":");
        int min = Integer.parseInt(parts[0]);
        int sec = Integer.parseInt(parts[1]);
        return min * 60 + sec;
    }
    
    private static String secondsToTime(int time){
        int min = time / 60;
        int sec = time % 60;
        
        return String.format("%02d:%02d",min,sec);
    }
}