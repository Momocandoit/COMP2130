package com.example.nov19;

// --------- abstract and interface --------------

// 普通のクラス
class EnglishGreeting {
    void sayHello() {
        System.out.println("Hello");
    }
}

// Abstract class
abstract class Greeting {
    abstract void Hello();    // ;で終わるのがポイント
}

// abstract and interfaceの違い
/* abstract: is a　の共通土台。継承・実装は1つのみ(extends)。
 *
 * interface: 何ができるか。継承・実装は複数可(implements)。
 *
 */

// -----