# vn.hieplq.asynshutdown
demo behavior shutdown by async and normal with and without console

async shutdown difference from normal shutdown by
1. async shutdown will wait non-deamon thead terminal to shutdown jvm
2. async shutdown with hold terminal jvm when has console thread active (run with -console)

reason console thread not terminal maybe relative this one [362412](https://bugs.eclipse.org/bugs/show_bug.cgi?id=362412), but why normal shutdown don't get hold issue?
