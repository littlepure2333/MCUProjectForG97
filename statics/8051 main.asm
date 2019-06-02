    LENGTH EQU 3    ; 数据包的长度
    RDFA   EQU 40H  ; 接受数据块首地址 (Receive Data First Address)
                    ; MCU接受的数据块放置在内部RAM区的40～4F单元中
    SDFA   EQU 50H  ; 发送数据块首地址 (Send Data First Address)
                    ; MCU发送的数据块放置在内部RAM区的50～5F单元中
    KEYBOARDTEMP EQU 60H
    
    LED_SEND_INIT       EQU 0x10
    LED_SEND_FLASH      EQU 0x11
    KEYBOARD_INIT       EQU 0x12;To check the keyboard interface
    TAKEORRETURN_INIT   EQU 0x13;To check the takeorreturn function interface
    YES_INIT            EQU 0x14;To check the admit function interface
    LCD_INIT            EQU 0x15
    LCD_HELLO           EQU 0x16
    LCD_CHOOSE_ACT      EQU 0x17
    LCD_ID_NOT_EXIST    EQU 0x18
    LCD_ID_INVALID      EQU 0x19
    DATA_END            EQU 0x7F
;   这里放别的数据类型

    RS EQU P1.5
    RW EQU P1.6
    EN EQU P1.7
    LCM_DATA EQU P0    ;lcd
    

;...............................................................

    ORG 0000H
    LJMP MAIN
    ORG 0003H       ; External 0 中断程序入口
    RETI            ; 中断返回
    ORG 000BH       ; Timer 0 中断程序入口
    RETI            ; 中断返回
    ORG 0013H       ; External 1 中断入口
    RETI            ; 中断返回
    ORG 001BH       ; Timer 1 中断程序入口
    RETI            ; 中断返回
    ORG 0023H       ; Serial port 中断程序入口
    RETI            ; 中断返回
    ORG 002BH       ; Timer 2 中断程序入口    
    RETI            ; 中断返回

    ORG 100h
    
MAIN:
    MOV SP, #1FH    ; 设置堆栈指针
    ACALL UART_INIT ; 初始化串行通信
    
LOOP:
    ACALL UART_READ ; 接受数据块
    MOV A, RDFA     ; 接收数据块的第一个字节，代表数据类型
    ;MOV A,#13h
CHECK1:
    CJNE A, #LED_SEND_INIT, CHECK2  ; 判断该执行LED_SEND_INIT_FUNC吗
    ACALL LED_SEND_INIT_FUNC    ;
CHECK2:
    CJNE A,#LED_SEND_FLASH, CHECK3  ; 判断该执行LED_SEND_FLASH_FUNC吗
    ACALL LED_SEND_FLASH_FUNC   ;
CHECK3:
    CJNE A,#KEYBOARD_INIT,CHECK4; To check if it should go to the function KEYBOARD_INIT
    ACALL KEYBOARD
CHECK4:
    CJNE A,#LCD_INIT,CHECK5; To check if it should go to the function LCD_INIT
    ACALL LCD_INIT
CHECK5:
    CJNE A,#LCD_HELLO,CHECK6; To check if it should go to the function LCD_HELLO
    ACALL LCD_HELLO
CHECK6:
    CJNE A,#LCD_CHOOSE_ACT,CHECK7; To check if it should go to the function YES_INIT
    ACALL LCD_CHOOSE_ACT
CHECK7:
    CJNE A,#LCD_ID_NOT_EXIST,CHECK8; To check if it should go to the function YES_INIT
    ACALL LCD_ID_NOT_EXIST
CHECK8:
    CJNE A,#LCD_ID_INVALID,LAST; To check if it should go to the function YES_INIT
    ACALL LCD_ID_INVALID
;   这里可以插入别的子程序
LAST:    SJMP LOOP       ; 循环

;......................................................................

UART_INIT: 
    MOV TMOD, #20h   ; set timer 1 to auto-reload 
    MOV TH1, #0FDh   ; set 9600 baud(@11.0592MHz) 
    SETB TR1         ; start timer 1
    MOV SCON, #50h   ; set 8-bit data and Mode 1
    RET

UART_READ: MOV R0, #RDFA ; 设置MCU接收的数据块首地址
           ;MOV R1, #LENGTH ; 设置接收数据块长度
RECEIVE:  JNB RI, $ ; 等待接收完一字节
          MOV A, SBUF ; 接受一字节数据
          CLR RI
          MOV @R0, A ; 存到接收数据块区域
          INC R0 ; 修改地址指针
          CJNE A, #DATA_END, RECEIVE ; 判断数据块是否接收完毕
RET

UART_SEND:
CLR TI ; clear transmit intr flag
MOV SBUF, A ; put byte in SBUF
JNB TI, $ ; wait till byte is sent
RET ; leave subroutine

LED_SEND_INIT_FUNC:
    ; todo
    RET

LED_SEND_FLASH_FUNC:
    ; todo
    RET

DELAY: MOV R4,#1
          THERE: MOV R5,#255;2
               HERE:MOV R6,#255;2
                    DJNZ R6,$ ;2*255
               DJNZ R5,HERE;2
           DJNZ R4,THERE
       RET;1

DELAY1: MOV R6, #45
    ERE:   MOV R5,#255;2
          DJNZ R5,$
        DJNZ R6, ERE
       RET;1

;.........................................................................Keyboard 9
;Assume the number delievered by JAVA is stored into 30h

;For the function initialization
KEYBOARD: MOV KEYBOARDTEMP,#70h
          MOV R4,#9
          MOV R1,#70h
;Clear the content in the address
KEYBOARDINIT: MOV @R1,#0x00
              INC R1
              DJNZ R4,KEYBOARDINIT     

;Perform the main function   
KEYBOARDMAIN: ACALL CHECKBUTTON;To check the button
              ACALL DELAY
              CJNE A,#0x00,KEYBOARDNEXT1;If A has no meaning, then start it again
              SJMP KEYBOARDENDLOGIN
      
      KEYBOARDNEXT1: CJNE A,#0x0C,KEYBOARDNEXT2;If want to delete one bit
                     DEC KEYBOARDTEMP;To get the current position
                     MOV R0,KEYBOARDTEMP;To move the position of R0
                     CJNE R0,#0x6F,KEYBOARDCONTINUE1;To set the bound of deleting
                     INC KEYBOARDTEMP;If delete too much
                     ACALL RM_DATA              ;LCD remove char
                     SJMP KEYBOARDENDLOGIN
                     
      KEYBOARDCONTINUE1: MOV @R0,#0x00;Set the number want to delete to be 00h
                         SJMP KEYBOARDENDLOGIN
                         
      KEYBOARDNEXT2: CJNE A,#0x0B,KEYBOARDNEXT3;If press confirm button
                     MOV R4,#9;R4 is the recording of the amount of number to be transmitted
                     MOV R1,#70h;The position of the first number
                     MOV A,#0x30;Transmit the head
                     ACALL UART_SEND
                     ACALL DELAY1
             
      KEYBOARDTRANS: MOV A,@R1;Transmit the number in the position
                     ACALL UART_SEND
                     ACALL DELAY1
                     INC R1;Move to the next
                     DJNZ R4,KEYBOARDTRANS
                     MOV A,#7Fh;Transmit the tail
                     ACALL UART_SEND
                     ACALL DELAY1
                     
      ;Reset all the value in the position
      MOV R4,#9
      MOV R1,#78h
      KEYBOARDRESET: MOV @R1,#0x00
                     DEC R1
                     DJNZ R4,KEYBOARDRESET
      ;Move the temp position to the head
      MOV KEYBOARDTEMP,#70h
      RET

      ;Fault tolerance    
      KEYBOARDNEXT3: CJNE A,#0xF7,KEYBOARDNEXT4
                     SJMP KEYBOARDENDLOGIN
                     
      ;To send the number to LCD and store it
      KEYBOARDNEXT4: MOV R0,KEYBOARDTEMP;Store it in the temp position
                     INC KEYBOARDTEMP;Move it to the next position
                     CJNE R0,#0x79,KEYBOARDCONTINUE2;To set the bound of storing
                     DEC KEYBOARDTEMP;If it overflows, then add it back
                     SJMP KEYBOARDENDLOGIN

      KEYBOARDCONTINUE2: MOV @R0,A;Store it
                        CJNE A,#0AH,NOT_0
                        ADD A,#26H
                        SJMP IS_0
                        NOT_0:
                            ADD A,#30H
                        IS_0:
                        ACALL W_DATA    ; LCD write char
                        SJMP KEYBOARDENDLOGIN
             
      KEYBOARDENDLOGIN: SJMP KEYBOARDMAIN


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;FUNCTION to choose take:33h return:34h
TAKEORRETURN: ACALL CHECKBUTTON
              ACALL DELAY
              
              CHECKTAKE: CJNE A,#0x01,CHECKRETURN;If press take, that is 1
                         MOV A,#33h
                         ACALL UART_SEND;Transmit the head
                         ACALL DELAY1
                         MOV A,#7Fh
                         ACALL UART_SEND;Transmit the tail
                         RET
              
              CHECKRETURN: CJNE A,#0x02,TAKEORRETURN;If press return, that is 1
                           MOV A,#34h
                           ACALL UART_SEND;Transmit the head
                           ACALL DELAY1
                           MOV A,#7Fh
                           ACALL UART_SEND;Transmit the tail
                           RET
              
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; 
CHOOSEYES: ACALL CHECKBUTTON
           ACALL DELAY
           CJNE A,#0x0B,CHOOSEYES;If press confirm, that is 1
           MOV A,#31h
           ACALL UART_SEND;Transmit the head
           MOV A,#7Fh
           ACALL UART_SEND;Transmit the tail
           RET
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;     
CHECKBUTTON: MOV DPTR,#KEYTABLE;Load the tabel
             ACALL KEY
             RET

KEY: ACALL KEY0_1   ;Call KEY0_1 to determine if a key has been pressed
     JB F0,$-2   ;Keyless press, ACALL KEY0_1 to continue the scan
     MOV A,R1   ;R1 is the code pointer
     MOVC A,@A+DPTR  ;Access code, send off display
     RET

KEY0_1: ;Key detection subroutine, equivalent to initialization
        SETB F0   ;Set F0 = 1
        MOV R3,#0F7H  ;The initial value of the line scan pointer (P2.3=0) detects the line 2.3
        MOV R1,#00H   ;The initial value of the code pointer is to look at the column
        
L2: MOV A,R3   ;Loading scan pointer
    MOV P2,A   ;Output to P2 and start scanning for a 0 line
    NOP
    MOV A,P2   ;Read in P2
    SETB C   
    MOV R5,#4   ;Check P2.7 ~ P2.4, a total of four columns
    
L3:  ;Check 4 columns
     RLC A   ;Move one digit left (P2.7~P2.4)
     JNC KEY1   ;C=0 detected means pressed
     INC R1   ;Keyless press adds 1 to the code pointer
     DJNZ R5,L3   ;Four columns detected already
     MOV A,R3   ;Loading scan pointer
     SETB C
     RRC A       ;Scan the next row at 0
     MOV R3,A   ;Store back to R3 scan pointer register
     JC L2   ;when C=0，Line scan completed
     RET

KEY1: CLR F0   ;F0 clear 0, means the key is pressed
      RET   

KEYTABLE:  
;To store the keyboard number
DB      0x0C;Delete
DB      0x0B;Confirm
DB      0x0A;0
DB      0x09;9

DB      0x08;8
DB      0x07;7
DB      0x06;6
DB      0x05;5

DB      0x04;4
DB      0x03;3
DB      0x02;2
DB      0x01;1

DB      0x00;Not in use
DB      0x00;Not in use
DB      0x00;Not in use
DB      0x00;Not in use

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;   
;LCD MODULE

; lcd strings

TAB_HELLO: DB 'Input your id:'
TAB_CHOOSE_ACT: DB 'Take/Return? 1/0'
TAB_ID_NOT_EXIST: DB 'ID doesnot exist'
TAB_ID_INVALID: DB 'Invaild ID!' 

LCD_HELLO:
MOV DPTR, #TAB_HELLO
MOV R0, #14
ACALL W_STR
RET

LCD_CHOOSE_ACT:
MOV DPTR, #TAB_CHOOSE_ACT
MOV R0, #16
ACALL W_STR
RET

LCD_ID_NOT_EXIST: 
MOV DPTR, #TAB_ID_NOT_EXIST
MOV R0, #16
ACALL W_STR
RET

LCD_ID_INVALID: 
MOV DPTR, #TAB_ID_INVALID
MOV R0, #11
ACALL W_STR
RET

LCD_INIT:
MOV A,#00111000B		;总线8，行数2，小字
ACALL W_INST
MOV A,#00001000B		;关闭显示，光标和闪烁
ACALL W_INST
ACALL LCD_CLR            ;clear
MOV A,#00000110B		;mode-输入后光标R移，屏幕不动
ACALL W_INST
MOV A,#00001111B		;开启显示，开启光标和闪烁
ACALL W_INST
RET

LAY:                ;读忙程序
PUSH 7
MOV LCM_DATA,#0FFH
MOV R7,#255
CLR RS
SETB RW
SETB EN
POLLBF:
JNB LCM_DATA.7, POLLOK
DJNZ R7,POLLBF
POLLOK:
CLR EN
POP 7
RET


W_INST:				;input lcd instruction
ACALL LAY
CLR RS
CLR RW
SETB EN
MOV LCM_DATA,A
CLR EN
RET

W_DATA:				;input single character
ACALL LAY
SETB RS
CLR RW
SETB EN
MOV LCM_DATA, A
CLR EN
RET

RM_DATA:            ;退位
MOV A, #00010000B   ;退格
ACALL W_INST
MOV A, #00100000B   ;empty character
ACALL W_DATA
MOV A, #00010000B   ;退格
ACALL W_INST
RET

W_STR:				    ;input string
PUSH ACC
ACALL LCD_CLR
MOV A,#10000000B		;设置起始读写地址（第一行）
ACALL W_INST
CLR A
LOOP1:
MOVC A,@A+DPTR
ACALL W_DATA
INC DPTR
CLR A
DJNZ R0, LOOP1
MOV A,#11000000B        ;设置起始读写地址（第二行）
ACALL W_INST
POP ACC
RET

LCD_CLR:				;clear the screen
MOV A,#01H
ACALL W_INST
RET

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;  

END         