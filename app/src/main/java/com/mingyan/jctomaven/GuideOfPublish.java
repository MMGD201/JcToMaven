package com.mingyan.jctomaven;

public class GuideOfPublish {
/**20200519AndroidStudio3.5.3。Publish Maven from JCenter, fail two time. Strive try again.
 * https://inthecheesefactory.com/blog/how-to-upload-library-to-jcenter-maven-central-as-dependency/en，使用此來測試publish到JCenter & Maven Center。
 * 先創Library，File> New> New Moudle> Android Libray，
 * app最好File> Project Structure> Dependencies> app> + > Module Dependency 創立的Library來用以在本Application演示使用範例，
 * 在build.gradle(Project:xxx)的dependencies { }裡面增加
 * classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.4'
 * classpath 'com.github.dcendents:android-maven-gradle-plugin:2.1'
 * ------再local.properties(SDK Location)增加，數值用到第3部分操作產生的，但非第一次用就直接貼上，詳看第4部分------
 * bintray.user=BINTRAY帳戶名
 * bintray.apikey=Bintray的下拉有登出選項處選Edit Profile > API Key > 輸入Bintray密碼就有了
 * bintray.gpg.password=這是第3部分生成gpg key時候填入的密碼 ( 或翻譯是填入的密碼短語 )
 * 再來是build.gradle(Module:該xxxLibrary)，
 * build.gradle(Module:jctomc)要增加ext { xxxx... }，
 * build.gradle(Module:jctomc)要增加apply from: 'https://raw.githubusercontent.com/nuuneoi/JCenter/master/installv1.gradle'
 * build.gradle(Module:jctomc)要增加apply from: 'https://raw.githubusercontent.com/nuuneoi/JCenter/master/bintrayv1.gradle'
 * 這兩個apply from增加在{ }之外各自單獨一行，要一模一樣不用改nuuneoi。
 * 這些Code是第4部分：準備一個Android Studio項目   之中有說明，
 * 但因為自己改成先創立AndroidStudio項目所以要補上。
 * 並且VSC> Import into Version Control> Share Project on GitHub，
 *
 * 第2部分：為Maven Central創建Sonatype帳戶
 * 因為有創過Bintray & Sonatype帳號，直接從第二步的Sonatype web創 Issues開始進行，
 * --必填欄位...填完按Create--
 * Summary: Request a permission to distribute library to Maven Central.  [  Summary可以隨便輸入 ]
 * GroupId: com.github.mmgd201    [ 因為怕publish到Maven Center的Domain不能瞎掰所以，GroupId用GitHub創的帳號 ]
 * Project URL: https://github.com/MMGD201/JcToMaven
 * SCM url: https://github.com/MMGD201/JcToMaven.git
 * ----------------------------------
 * 再來去Bintray的下拉有登出選項處選Edit Profile > Accounts > 選google登入、選GitHub登入, 都按授權，
 * 然後Sonatype OSS User填sonatype帳號名，Sonatype OSS password填sonatype密碼，按Update。
 *
 * 第3部分：在Bintray中啟用自動簽名
 * 由於已經按以下說明用過所以本次跳過不重複以看能否成功。
 * 先安裝Git Bash，通過命令行生成密鑰 gpg --gen-key，查看創建的密鑰的信息gpg --list-keys，
 * 將公共密鑰上載到密鑰服務器以使其有用 gpg --keyserver hkp://pool.sks-keyservers.net --send-keys  PUBLIC_KEY_ID( P_K_ID替代為上一指令產生的pub 後面的長串)
 * 將公用密鑰導出為ASCII裝甲格式  gpg -a --export xxxx@email.com > public_key_sender.asc
 * 將專用密鑰導出為ASCII裝甲格式  gpg -a --export-secret-key xxxx@email.com > private_key_sender.asc
 * 導出ASCII裝甲格式文件用Git Bash指令gpg --version到顯示Home : 的位置找就有了。
 * 去Bintray的下拉有登出選項處選Edit Profile > GPG Signing的Public Key裡面用public_key_sender.asc文件用筆記本打開全複製填上，
 * Private Key裡面用private_key_sender.asc文件用筆記本打開全複製填上，
 * 再來是啟用自動簽名，
 * 轉到Bintray的主頁在Add New Repository選項附近點創建了的xxxpackage(maven)或是xxxRepository(maven)，
 * 進去再點Owned by  xxx右邊有 Edit 按鈕點下去，
 * 將GPG sign uploaded files automatically 框啟用/勾選，單擊更新  以保存進度。從現在開始，
 * 上傳到我們的Maven資源庫的每個庫都將被自動簽名，只需單擊一下即可轉發到Maven Central。
 *
 * 第4部分：準備一個Android Studio項目
 * File > New > New Moudle > Android Library。
 * build.gradle(Project) 的 dependencies {
 *      classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.4'
 *      classpath 'com.github.dcendents:android-maven-gradle-plugin:2.1'
 * }
 * local.properties(SDK Location)增加
 * bintray.user=BINTRAY帳戶名
 * bintray.apikey=Bintray的下拉有登出選項處選Edit Profile > API Key > 輸入Bintray密碼就有了
 * bintray.gpg.password=這是第3部分生成gpg key時候填入的密碼 ( 或翻譯是填入的密碼短語 )
 * 再來是build.gradle(Module:該xxxLibrary)，增加ext { xxxx... }，
 * apply from: 'https://raw.githubusercontent.com/nuuneoi/JCenter/master/installv1.gradle'
 * apply from: 'https://raw.githubusercontent.com/nuuneoi/JCenter/master/bintrayv1.gradle'
 * 這兩個apply from增加在{ }之外各自單獨一行，要一模一樣不用改nuuneoi。
 *
 * 第5部分：將庫上傳到您的Bintray空間
 * 轉到Android Studio上的Terminal，檢查代碼的正確性並構建庫文件，輸入命令gradlew install，沒錯將顯示BUILD SUCCESSFUL，
 * 將生成的文件上傳到bintray，輸入gradlew bintrayUpload 沒錯將顯示 SUCCESSFUL。
 * 到此Library尚未發到Maven Central，也沒在jcenter。*/
}
