package com.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-07-18 14:20
 * @Modified By:
 **/



public class DeleteDirectory implements FileVisitor {

	boolean deleteFileByFile(Path file) throws IOException {
		return Files.deleteIfExists(file);
	}

	@Override
	public FileVisitResult postVisitDirectory(Object dir, IOException exc)
			throws IOException {

		if (exc == null) {
			System.out.println("Visited: " + (Path) dir);
			boolean success = deleteFileByFile((Path) dir);

			if (success) {
				System.out.println("Deleted: " + (Path) dir);
			} else {
				System.out.println("Not deleted: " + (Path) dir);
			}
		} else {
			throw exc;
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs)
			throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Object file, BasicFileAttributes attrs)
			throws IOException {
		boolean success = deleteFileByFile((Path) file);

		if (success) {
			System.out.println("Deleted: " + (Path) file);
		} else {
			System.out.println("Not deleted: " + (Path) file);
		}

		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Object file, IOException exc)
			throws IOException {
		//report an error if necessary

		return FileVisitResult.CONTINUE;
	}
}

class Main {

	public static void main(String[] args) throws IOException {

		Path directory = Paths.get("D:/rafaelnadal");
		DeleteDirectory walk = new DeleteDirectory();
		EnumSet opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

		Files.walkFileTree(directory, opts, Integer.MAX_VALUE, walk);
	}
}
