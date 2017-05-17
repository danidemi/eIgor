package it.infocert.eigor.test;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.file.Files.setPosixFilePermissions;


public class Files {

    /**
     * Build a new {@link File} using the provided components and the correct separator.
     */
    public static File newFile(String root, String... pathComponents) {
        return newFile(new StringBuilder( root ), pathComponents);
    }

    /**
     * Build a new {@link File} using the provided components and the correct separator.
     */
    public static File newFile(File folder, String... pathComponents) {
        return newFile(new StringBuilder( folder.toString() ), pathComponents);
    }

    /** Find first file in a given folder, or {@code null}. */
    public static File findFirstFileOrNull(File outputDir, Predicate<File> col) {
        return Arrays.stream(outputDir.listFiles()).filter(col).findFirst().orElse(null);
    }

    /**
     * Unzip a zip file.
     * @param zipFile Input Zip File
     * @param outputFolder Where the zip will be uncompressed.
     */
    public static void unzip(File zipFile, File outputFolder) throws Exception {
        // inspired by http://www.mkyong.com/java/how-to-decompress-files-from-a-zip-file/

        ZipInputStream zis = null;
        FileOutputStream fos = null;
        ZipEntry ze = null;
        try{
            byte[] buffer = new byte[1024];


            //create output directory is not exists
            if(!outputFolder.exists()){
                outputFolder.mkdir();
            }

            //get the zip file content
            zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ze = zis.getNextEntry();

            while(ze!=null){

                String fileName = ze.getName();

                if(ze.isDirectory()){
                    File newFolder = new File(outputFolder, fileName);
                    newFolder.mkdirs();
                }else{
                    File newFile = new File(outputFolder, fileName);

                    //create all non exists folders
                    //else you will hit FileNotFoundException for compressed folder
                    File file = new File(newFile.getParent());
                    file.mkdirs();

                    fos = new FileOutputStream(newFile);

                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }

                    fos.close();
                }


                ze = zis.getNextEntry();
            }


        }finally {
            if(zis!=null) zis.closeEntry();
            IOUtils.closeQuietly(zis);
            IOUtils.closeQuietly(fos);
        }

    }

    public static void untar(File tarGzFile, File outputFolder) throws Exception {

        /** create a TarArchiveInputStream object. **/

        FileInputStream fin = new FileInputStream(tarGzFile);
        BufferedInputStream in = new BufferedInputStream(fin);
        GzipCompressorInputStream gzIn = new GzipCompressorInputStream(in);
        TarArchiveInputStream tarIn = new TarArchiveInputStream(gzIn);

        TarArchiveEntry entry = null;

        /** Read the tar entries using the getNextEntry method **/

        while ((entry = (TarArchiveEntry) tarIn.getNextEntry()) != null) {

            /** If the entry is a directory, create the directory. **/

            if (entry.isDirectory()) {

                String[] theNewFolder = entry.getName().split(File.separator);
                File f = newFile(outputFolder, theNewFolder);
                f.mkdirs();
            }
            /**
             * If the entry is a file,write the decompressed file to the disk
             * and close destination stream.
             **/
            else {
                int count;
                int buffer = 2048;
                byte data[] = new byte[buffer];

                File root = outputFolder.getAbsoluteFile();
                String fileInTarGz = entry.getName();
                String[] split = fileInTarGz.split(File.separator);
                File theNewInflatedFile = newFile(root, split);

                File folderOfTheNewInflatedFile = theNewInflatedFile.getParentFile();
                folderOfTheNewInflatedFile.mkdirs();

                theNewInflatedFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(theNewInflatedFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos,
                        buffer);
                while ((count = tarIn.read(data, 0, buffer)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.close();
            }
        }

        /** Close the input stream **/

        tarIn.close();

    }


    public static void setPermission(File file, PosixFilePermission... permissions) throws IOException{

        Set<PosixFilePermission> perms = new HashSet<>();

        for (PosixFilePermission permission : permissions) {
            perms.add(permission);
        }

        setPosixFilePermissions(file.toPath(), perms);
    }

    private static File newFile(StringBuilder pathname, String[] pathComponents) {
        for (String component : pathComponents) {
            pathname.append(File.separator);
            pathname.append(component);
        }
        return new File(pathname.toString());
    }


    private Files() {
        throw new UnsupportedOperationException("Cannot be instatiated.");
    }

}
