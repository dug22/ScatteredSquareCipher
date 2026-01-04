## Brief Introduction

After learning about classical ciphers like the PlayFair cipher, the Nihilist cipher, etc, I decided to create my own custom cipher for fun, and it is called the Scatter  
Square Cipher. I decided to implement the Scatter Square Cipher using Java. I will be explaining how the the scatter square cipher works step by step.

---

# Getting Started

## Drawing Out The Polybius Square

You start out the cipher by creating an empty 6x6 polybius square, as this cipher allows characters A-Z along with 0-9, which adds up to 36 characters.

|   | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|
| 1 |   |   |   |   |   |   |
| 2 |   |   |   |   |   |   |
| 3 |   |   |   |   |   |   |
| 4 |   |   |   |   |   |   |
| 5 |   |   |   |   |   |   |
| 6 |   |   |   |   |   |   |

---

## Choosing Our Plaintext and Key

Next, write down the message you wish to encrypt, along with your key. Your message and key must have the same length, and length is accounted for if both the message and key contain allowed 
characters. Make sure your key is composed of random letters and numbers. This will result in a one time padding situation, which is what we are aiming for.

```
Message: HELLO WORLD
Key:     PQOWIEURM0
```
Both the message and key have a length of 10. Since spaces aren't an appropriate character, they will not be accounted for.

## Selecting Coordinates for the Key’s Characters
The Scattered Square Cipher allows you to choose the appropriate coordinates to record for each character within the key to be placed within your polybius square. So I am going to choose where our key's characters should be placed within our 6x6 polybius 
square. The first coordinate is our row, and our second coordinate is our column

```
P = {2,3}
Q = {3,2}
O = {4,4}
W = {2,4}
I = {5,4}
E = {4,3}
U = {5,3}
R = {5,2}
M = {3,6}
0 = {2,6}
```
Our 6x6 polybius square should look something like this:

```
-- 6x6 Polybius Square --
| - | - | - | - | - | - |
| - | - | P | W | - | 0 |
| - | Q | - | - | - | M |
| - | - | E | O | - | - |
| - | R | U | I | - | - |
| - | - | - | - | - | - |
-------------------------
```

## Filling Our 6x6 Polybius Square

For the cells that are empty, fill them up with characters that are allowed (A-Z 0-9) in order. Remember that you can only list each character once. Our final polybius would look something like this:
```
-- 6x6 Polybius Square --
| A | B | C | D | F | G |
| H | J | P | W | K | 0 |
| L | Q | N | S | T | M |
| V | X | E | O | Y | Z |
| 1 | R | U | I | 2 | 3 |
| 4 | 5 | 6 | 7 | 8 | 9 |
-------------------------
```

## Encrypting Our Plaintext
The encryption process for the Scatter Square Cipher is straightforward.

  - First, locate the plaintext character within the 6×6 Polybius square and determine its linear index, counting row by row from top-left to bottom-right (A = 0, B = 1, …, Z = 25, 0 = 26, 1 = 27, …, 9 = 35).
  -  Then, shift the character forward by the index of the corresponding key character, wrapping through the square row by row as needed.

For example to encrypt the letter locate the letter H within our polybius square. H is in row 2 column 1 or index 7. Now we want to count 15 places to the right row by row, because P has an index of 15.
H becomes O. Now let's solve the rest for the of the characters.


- E's index is 21 in the polybius square. Q's index is 16. Couting forward 16 spaces results in E becoming A.
- L's index is 13 in the polybius square. O's index is  14. Counting forward 14 spaces results in L becoming U
- L's index is 13 in the polybius square. W's index is 22. Couting forward 22 spaces results in L becoming 8.
- O's index is 22 in the polybius square. I's index is 8. Counting forward 8 spaces results in O becoming 3.
- W's index is 10 in the poybius square. E's index is 4. Couting forward 4 spaces results in W becoming Q.
- O's index is 22 in the polybius square. U's index is 21. Counting forward 21 spaces results in O becoming G.
- R's index is 26 in the polybius square. R's index is 17. Counting forward 17 spaces results in R becoming H
- L's index is 13 in the polybius square. M's index is 12. Counting forward 12 spaces results in L becoming 1.
- D's index is 4 in the polybius square. 0's index is 26. Couting forward 26 spaces results in D becoming 3.

**Final Encrypted Message:** OAU83 QGH13

## Decrypting our Ciphertext:
The decryption process works in the same way as the encryption process, except that it counts backwards from the location of the ciphertext character within the polybius square. You count backwards X amount of spaces based on the index of the key. 

- O's index is 22 in the polybius square. P's index is 15. Couting backwards 15 spaces results in O becoming H.
- A's index is 1 in the polybius square. Q's index is 16. Couting backwards 16 spaces results in A becoming E.
- U's index is 27 in the polybius square. O's index is 14. Counting backwards 14 spaces results in U becoming L
- 8's index is 35 in the polybius square. W's index is 22. Couting backwards 22 spaces results in 8 becoming L.
- 3's index is 30 in the polybius square. I's index is 8. Counting backwards 8 spaces results in 3 becoming O.
- Q's index is 14 in the poybius square. E's index is 4. Couting backwards 4 spaces results in Q becoming W.
- G's index is 6 in the polybius square. U's index is 21. Counting backwards 21 spaces results in G becoming O.
- H's index is 7 in the polybius square. R's index is 17. Counting backwards 17 spaces results in H becoming R
- 1's index is 25 in the polybius square. M's index is 12. Counting backwards 12 spaces results in 1 becoming L.
- 3's index is 30 in the polybius square. 0's index is 26. Couting backwards 26 spaces results in 3 becoming D.

**Final Decrypted Message:** HELLO WORLD.
