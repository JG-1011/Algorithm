import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 랭킹전
 * 입장 > 자신과 비슷한 레벨의 플레이어를 매칭
 * 입장 > 매칭
 * 만약 매칭이 불가하다면 새로운 방을 생성하고 입장한다.
 * 방만든사람 기준으로 플마 10까지 입장 가능
 *
 * 입장 가능한 방이 있다면 입장 시킨후 정원이 찰 때까지 대기
 * 입장 가능 방이 여러개면 먼저 생성된 방에 입장
 *
 * 모두 차면 게임 시작
 *
 * 닉넹미 사전순으로 출력해야 함
 *
 * 플레이 수 p
 * 닉네임 n
 * 레벨 l
 * 정원 n
 *
 * p,m
 * l n
 *
 * 레벨과 닉네임을 쌍으로 저장해야해
 * 방 만들어진 순서도 알아야 해
 * 방장도 알아야 해
 * 리스트 속 리스트를 만들어야 하나?
 *
 * */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();
            Player player = new Player(l, n);

            boolean added = false;

            for (Room room : rooms) {
                if (room.canEnter(player)) {
                    room.addPlayer(player);
                    added = true;
                    break;
                }
            }

            if (!added) {
                Room newRoom = new Room(l, m);
                newRoom.addPlayer(player);
                rooms.add(newRoom);
            }
        }
        for (Room room : rooms) {
            room.printRoomsStatus();
        }
    }
}

class Room {
    int min, max, capacity;
    List<Player> players = new ArrayList();

    public Room(int level, int capacity) {
        this.min = level - 10;
        this.max = level + 10;
        this.capacity = capacity;
    }

    boolean canEnter(Player player) {
        return min <= player.level && player.level <= max && players.size() < capacity;
    }

    void addPlayer(Player player) {
        players.add(player);
    }

    boolean isFull() {
        return players.size() == capacity;
    }

    void printRoomsStatus() {
        Collections.sort(players);
        if (isFull()) {
            System.out.println("Started!");
        } else {
            System.out.println("Waiting!");
        }
        for (Player player : players) {
            System.out.println(player.level + " " + player.name);
        }
    }
}

class Player implements Comparable<Player> {
    int level;
    String name;

    public Player(int level, String name) {
        this.level = level;
        this.name = name;
    }

    @Override
    public int compareTo(Player o) {
        return this.name.compareTo(o.name);
    }
}