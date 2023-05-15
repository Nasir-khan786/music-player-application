package com.playList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

	private static ArrayList<Album> albums = new ArrayList<>();

	public static void main(String[] args) {

		Album album = new Album("Album1", "English Songs");

		album.addSong("I Don't care", 2.09);
		album.addSong("Shape of you", 3.53);
		album.addSong("You Belong with me", 3.51);

		albums.add(album);

		Album album1 = new Album("Album2", "Hindi Songs");
		album1.addSong("Kesariya", 4.28);
		album1.addSong("O Kabira", 4.30);
		album1.addSong("Bolna", 3.33);
		album1.addSong("Hawayein", 4.50);

		albums.add(album1);
		
		Album album2 = new Album("Album3", "Punjabi Songs");
		album2.addSong("Lamberghini", 3.36);
		album2.addSong("Chann sitare", 3.33);
		album2.addSong("Na Ja", 3.28);
		album2.addSong("Kya Baat Hai", 2.58);
		
		albums.add(album2);

		// Add song into playList
		LinkedList<Song> playList1 = new LinkedList<>();
		albums.get(0).addToPlayList("Shape of you", playList1);
		albums.get(0).addToPlayList("You Belong with me", playList1);
		albums.get(1).addToPlayList("O Kabira", playList1);
		albums.get(1).addToPlayList("Hawayein", playList1);
		albums.get(1).addToPlayList("Kesariya", playList1);
		albums.get(2).addToPlayList("Chann sitare", playList1);
		albums.get(2).addToPlayList("Kya Baat Hai", playList1);

		play(playList1); // to play the playlist 1
	}

	private static void play(LinkedList<Song> playList) {
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		boolean forward = true;
		ListIterator<Song> listIterator = playList.listIterator();

		if (playList.size() == 0) {
			System.out.println("This playList have no songs");
		} else {
			System.out.println("Now playing " + listIterator.next().toString());
			printMenu();
		}

		while (!quit) {
			int action = sc.nextInt();
			switch (action) {
			case 0:
				System.out.println("PlayList complete");
				quit = true;
				break;

			case 1:
				if (!forward) {
					if (listIterator.hasNext()) {
						listIterator.next();
					}
					forward = true;
				}
				if (listIterator.hasNext()) {
					System.out.println("Now Playing " + listIterator.next().toString());
				} else {
					System.out.println("no song available, reached to the end of the list");
					forward = false;
				}
				break;

			case 2:
				if (forward) {
					if (listIterator.hasPrevious()) {
						listIterator.previous();
					}
					forward = false;
				}
				if (listIterator.hasPrevious()) {
					System.out.println("Now Playing " + listIterator.previous().toString());
				} else {
					System.out.println("We reached to first song");
					forward = false;
				}
				break;

			case 3:
				if (forward) {
					if (listIterator.hasPrevious()) {
						System.out.println("Now Playing " + listIterator.previous().toString());
						forward = false;
					} else {
						System.out.println("We are at the start of the list");
					}
				} else {
					if (listIterator.hasNext()) {
						System.out.println("Now playing " + listIterator.next().toString());
						forward = true;
					} else {
						System.out.println("We have reached to the end of list");
					}
				}
				break;

			case 4:
				printList(playList);
				break;

			case 5:
				printMenu();
				break;

			case 6:
				if (playList.size() > 0) {
					listIterator.remove();
					if (listIterator.hasNext()) {
						System.out.println("Now playing " + listIterator.next().toString());
					} else {
						if (listIterator.hasPrevious()) {
							System.out.println("Now playing " + listIterator.previous().toString());
						}
					}
				}
			}
		}

	}

	private static void printMenu() {
		System.out.println("Available options\n press");
		System.out.println(
				"0 - to quit\n" + "1 - to play next song\n" + "2 - to play previous song\n" + "3 - to replay the current song\n"
						+ "4 - list of All songs\n" + "5 - print all available options\n" + "6 - to delete current song");
	}

	private static void printList(LinkedList<Song> playList) {
		Iterator<Song> iterator = playList.iterator();
		System.out.println("----------------------");

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println("----------------------");

	}
}
