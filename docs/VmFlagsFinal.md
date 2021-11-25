VM查看相关信息

jdk1.7 默认垃圾收集器Parallel Scavenge（新生代）+Parallel Old（老年代）

jdk1.8 默认垃圾收集器Parallel Scavenge（新生代）+Parallel Old（老年代）

jdk1.9 默认垃圾收集器G1

-XX:+PrintCommandLineFlagsjvm参数可查看默认设置收集器类型

-XX:+PrintGCDetails亦可通过打印的GC日志的新生代、老年代名称判断

JDK11 ZGC为何如此之快, R大的介绍 https://blog.csdn.net/qq_41790443/article/details/82953853

例如: Use开头的VMFlags

version: 1.8.0_102

```
bool UnlinkSymbolsALot                         = false                               {product}
     bool Use486InstrsOnly                          = false                               {ARCH product}
     bool UseAES                                    = true                                {product}
     bool UseAESIntrinsics                          = true                                {product}
     intx UseAVX                                    = 1                                   {ARCH product}
     bool UseAdaptiveGCBoundary                     = false                               {product}
     bool UseAdaptiveGenerationSizePolicyAtMajorCollection  = true                                {product}
     bool UseAdaptiveGenerationSizePolicyAtMinorCollection  = true                                {product}
     bool UseAdaptiveNUMAChunkSizing                = true                                {product}
     bool UseAdaptiveSizeDecayMajorGCCost           = true                                {product}
     bool UseAdaptiveSizePolicy                     = true                                {product}
     bool UseAdaptiveSizePolicyFootprintGoal        = true                                {product}
     bool UseAdaptiveSizePolicyWithSystemGC         = false                               {product}
     bool UseAddressNop                             = true                                {ARCH product}
     bool UseAltSigs                                = false                               {product}
     bool UseAutoGCSelectPolicy                     = false                               {product}
     bool UseBMI1Instructions                       = false                               {ARCH product}
     bool UseBMI2Instructions                       = false                               {ARCH product}
     bool UseBiasedLocking                          = true                                {product}
     bool UseBimorphicInlining                      = true                                {C2 product}
     bool UseBoundThreads                           = true                                {product}
     bool UseCLMUL                                  = true                                {ARCH product}
     bool UseCMSBestFit                             = true                                {product}
     bool UseCMSCollectionPassing                   = true                                {product}
     bool UseCMSCompactAtFullCollection             = true                                {product}
     bool UseCMSInitiatingOccupancyOnly             = false                               {product}
     bool UseCRC32Intrinsics                        = true                                {product}
     bool UseCodeCacheFlushing                      = true                                {product}
     bool UseCompiler                               = true                                {product}
     bool UseCompilerSafepoints                     = true                                {product}
     bool UseCompressedClassPointers               := true                                {lp64_product}
     bool UseCompressedOops                        := true                                {lp64_product}
     bool UseConcMarkSweepGC                        = false                               {product}
     bool UseCondCardMark                           = false                               {C2 product}
     bool UseCountLeadingZerosInstruction           = false                               {ARCH product}
     bool UseCountTrailingZerosInstruction          = false                               {ARCH product}
     bool UseCountedLoopSafepoints                  = false                               {C2 product}
     bool UseCounterDecay                           = true                                {product}
     bool UseDivMod                                 = true                                {C2 product}
     bool UseDynamicNumberOfGCThreads               = false                               {product}
     bool UseFPUForSpilling                         = true                                {C2 product}
     bool UseFastAccessorMethods                    = false                               {product}
     bool UseFastEmptyMethods                       = false                               {product}
     bool UseFastJNIAccessors                       = true                                {product}
     bool UseFastStosb                              = true                                {ARCH product}
     bool UseG1GC                                   = false                               {product}
     bool UseGCLogFileRotation                      = false                               {product}
     bool UseGCOverheadLimit                        = true                                {product}
     bool UseGCTaskAffinity                         = false                               {product}
     bool UseHeavyMonitors                          = false                               {product}
     bool UseHugeTLBFS                              = false                               {product}
     bool UseInlineCaches                           = true                                {product}
     bool UseInterpreter                            = true                                {product}
     bool UseJumpTables                             = true                                {C2 product}
     bool UseLWPSynchronization                     = true                                {product}
     bool UseLargePages                             = false                               {pd product}
     bool UseLargePagesInMetaspace                  = false                               {product}
     bool UseLargePagesIndividualAllocation         = false                               {pd product}
     bool UseLinuxPosixThreadCPUClocks              = true                                {product}
     bool UseLockedTracing                          = false                               {product}
     bool UseLoopCounter                            = true                                {product}
     bool UseLoopInvariantCodeMotion                = true                                {C1 product}
     bool UseLoopPredicate                          = true                                {C2 product}
     bool UseMathExactIntrinsics                    = true                                {C2 product}
     bool UseMaximumCompactionOnSystemGC            = true                                {product}
     bool UseMembar                                 = false                               {pd product}
     bool UseMontgomeryMultiplyIntrinsic            = false                               {C2 product}
     bool UseMontgomerySquareIntrinsic              = false                               {C2 product}
     bool UseMulAddIntrinsic                        = false                               {C2 product}
     bool UseMultiplyToLenIntrinsic                 = true                                {C2 product}
     bool UseNUMA                                   = false                               {product}
     bool UseNUMAInterleaving                       = false                               {product}
     bool UseNewLongLShift                          = false                               {ARCH product}
     bool UseOSErrorReporting                       = false                               {pd product}
     bool UseOldInlining                            = true                                {C2 product}
     bool UseOnStackReplacement                     = true                                {pd product}
     bool UseOnlyInlinedBimorphic                   = true                                {C2 product}
     bool UseOprofile                               = false                               {product}
     bool UseOptoBiasInlining                       = true                                {C2 product}
     bool UsePSAdaptiveSurvivorSizePolicy           = true                                {product}
     bool UseParNewGC                               = false                               {product}
     bool UseParallelGC                             = false                               {product}
     bool UseParallelOldGC                          = false                               {product}
     bool UsePerfData                               = true                                {product}
     bool UsePopCountInstruction                    = true                                {product}
     bool UseRDPCForConstantTableBase               = false                               {C2 product}
     bool UseRTMDeopt                               = false                               {ARCH product}
     bool UseRTMLocking                             = false                               {ARCH product}
     bool UseSHA                                    = false                               {product}
     bool UseSHA1Intrinsics                         = false                               {product}
     bool UseSHA256Intrinsics                       = false                               {product}
     bool UseSHA512Intrinsics                       = false                               {product}
     bool UseSHM                                    = false                               {product}
     intx UseSSE                                    = 4                                   {product}
     bool UseSSE42Intrinsics                        = true                                {product}
     bool UseSerialGC                               = false                               {product}
     bool UseSharedSpaces                           = false                               {product}
     bool UseSignalChaining                         = true                                {product}
     bool UseSquareToLenIntrinsic                   = false                               {C2 product}
     bool UseStoreImmI16                            = false                               {ARCH product}
     bool UseStringDeduplication                    = false                               {product}
     bool UseSuperWord                              = true                                {C2 product}
     bool UseTLAB                                   = true                                {pd product}
     bool UseThreadPriorities                       = true                                {pd product}
     bool UseTransparentHugePages                   = false                               {product}
     bool UseTypeProfile                            = true                                {product}
     bool UseTypeSpeculation                        = true                                {C2 product}
     bool UseUnalignedLoadStores                    = false                               {ARCH product}
     bool UseVMInterruptibleIO                      = false                               {product}
     bool UseXMMForArrayCopy                        = true                                {product}
     bool UseXmmI2D                                 = false                               {ARCH product}
     bool UseXmmI2F                                 = false                               {ARCH product}
     bool UseXmmLoadAndClearUpper                   = true                                {ARCH product}
     bool UseXmmRegToRegMoveAll                     = true                                {ARCH product}
```