# mmieckowski_PhoneBookWithJava

# Stage 1/4: A needle in the hay
## Description
Have you ever had to use one of those 2000-plus page phone books full of all kinds of organizations </br>
and people's names printed in a small font in multiple columns on each page? </br>
Finding the information you need in such books can be an ordeal. As a matter of fact,</br>
searching through huge data sets may be a challenging task for a computer, too.</br>

In this project, you will create a phone book; you will implement several sorting and searching algorithms </br>
and compare their efficiency in dealing with a big dataset. For this, you will need to download</br>
the file directory.txt that contains the phone numbers of over a million people in multiple cities.</br>

## Objectives
In this stage, you should implement the simplest possible search to find the numbers of a few people</br>
whose names are listed in the file find.txt. The data is randomly sorted, so we should use a Linear Search </br>
to look through each of the directory entries until we find our target. While there may be faster ways to do this, </br>
avoid using optimizations like Maps in this stage - we'll get to optimizing in later stages.</br>

It takes a time to find all the records from the big file. We recommend you manually test your program </br>
with the simplified data: small_directory.txt and small_find.txt. However, to pass all the tests, </br>
you will have to work with the big files mentioned above.</br>

Note how long it takes you to do this when using a linear search so that you can compare results with other</br>
search methods.

### To measure the time difference, you can use System.currentTimeMillis().


Also notice that you don't need to read the file "directory.txt" again and again after each query. </br>
You should load all lines into memory and measure only the search process.</br>

Your program should output a message to show it has started searching, followed by a message stating </br>
how many entries out of the total number of targets in find.txt you were able to find along with the </br>
time taken to search for those entries (see example below).</br>

Please, do not keep the downloaded files inside your project directory because the server can reject large files</br>
and you will see the message "Failed to post submission to the Hyperskill".</br>

### Example
Below is an example of how your output should look:</br>

Start searching...
Found 500 / 500 entries. Time taken: 1 min. 56 sec. 328 ms.