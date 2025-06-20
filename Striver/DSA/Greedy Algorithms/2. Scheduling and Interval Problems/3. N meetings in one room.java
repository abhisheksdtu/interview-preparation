/*
    GREEDY APPROACH
    - Pair each meeting's start and end time as an object.
    - Sort all meetings by their end time.
    - Iterate through meetings, always select the next meeting that starts after the last selected meeting ends.
    - Count the number of non-overlapping meetings that can be attended.

    TIME: O(N) to build meeting objects
        + O(N log N) to sort by end time
        + O(N) to iterate and select meetings
        = O(N log N)

      - Building objects and counting is linear (O(N)).
      - Sorting the meetings dominates (O(N log N)).

    SPACE: O(N) for list of Meeting objects
      - Need space to store meeting details.

    Why?
    - Sorting is the slowest step.
    - Linear time to scan and select meetings.
*/
class Solution {
    public int maxMeetings(int[] start, int[] end) {
       int n = start.length;

       List<Meeting> list = new ArrayList<>();
       for (int i = 0; i < n; i++) {
           Meeting meeting = new Meeting(start[i], end[i], i+1);
           list.add(meeting);
       }

       list.sort(Comparator.comparing(Meeting::getEnd));

       int lastTime = 0;
       int count = 0;

       for (Meeting meeting : list) {
           if (meeting.getStart() > lastTime) {
               lastTime = meeting.getEnd();
               count++;
           }
       }

       return count;
    }
}

class Meeting {
    int start;
    int end;
    int position;

    public Meeting(int start, int end, int position) {
        this.start = start;
        this.end = end;
        this.position = position;
    }

    public int getEnd() {
        return this.end;
    }

    public int getStart() {
        return this.start;
    }
}
