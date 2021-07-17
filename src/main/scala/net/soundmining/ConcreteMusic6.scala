package net.soundmining

import net.soundmining.sound.{SoundPlay, SoundPlays}
import net.soundmining.synth.SuperColliderClient.loadDir
import net.soundmining.synth.{Instrument, SuperColliderClient}

object ConcreteMusic6 {

  implicit val client: SuperColliderClient = SuperColliderClient()
  val SYNTH_DIR = "/Users/danielstahl/Documents/Projects/soundmining-modular/src/main/sc/synths"
  val SOUND_DIR = "/Users/danielstahl/Documents/Music/sounds/Concrete Music 6_sounds/rendered_audio"

  val BIG_BOWL_2 = "big-bowl-2"
  val SMALL_BOWL_1 = "small-bowl-1"
  val MORTAR_RUMBLE_1 = "mortar-rumble-1"
  val MORTAR_HIT_1 = "mortar-hit-1"

  val soundPlays = SoundPlays(
    soundPlays = Map(
      BIG_BOWL_2 -> SoundPlay(s"$SOUND_DIR/Big Bowl 2.flac", 0.018, 3.222, spectrumFrequencies = Seq(469.726, 1156.29)),
      SMALL_BOWL_1 -> SoundPlay(s"$SOUND_DIR/Small Bowl 1.flac", 0.025, 1.454, spectrumFrequencies = Seq(1103.37, 2766.47)),
      MORTAR_RUMBLE_1 -> SoundPlay(s"$SOUND_DIR/Mortar Rumble 1.flac", 0.044, 1.309, spectrumFrequencies = Seq(), peakTimes = Seq(0.099, 0.228, 0.390, 0.503, 0.798, 0.864, 0.930, 1.030)),
      MORTAR_HIT_1 -> SoundPlay(s"$SOUND_DIR/Mortar Hit 1.flac", 0.051, 0.526)),
    numberOfOutputBuses = 64)

  def init(): Unit = {
    println("Starting up SuperCollider client")
    client.start
    Instrument.setupNodes(client)
    client.send(loadDir(SYNTH_DIR))
    soundPlays.init
  }

  def stop(): Unit = {
    println("Stopping SuperCollider client")
    client.stop
  }

  def recapitulation(): Unit = {
    val absoluteTimes = Seq(0.228, 0.503, 0.864)
    val ratio = (absoluteTimes(1) - absoluteTimes.head) / (absoluteTimes(2) - absoluteTimes.head) // 0.43238993710691825
    val invertedRatio = 1 - ratio // 0.5676100628930818
    val longTime = invertedRatio * 10 // 5.6761006289308185
    val shortTime = ratio * 10 // 4.323899371069182

    val times = Melody.absolute(0, Seq(
      longTime, longTime, longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime, shortTime * ratio,
      longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime * ratio,
      longTime, longTime))

    println(s"Times $times")

    client.resetClock

    val lowRate = 469.726 / 1156.29
    val lowLowRate = lowRate * lowRate

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times.head, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times.head + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times.head + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(1), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 0.6)
      .pan(0).play(times(1) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowRate)
      .pan(0.9).play(times(1) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowLowRate)
      .pan(-0.9).play(times(1) + 0.1, 10)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(2), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(2) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(2) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(3), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(3) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(3) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(4), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(4) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(4) + 0.02, 4)


    val lowHighRate = 1103.37 / 2766.47
    val lowLowHighRate = lowHighRate * lowHighRate

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(5), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(5) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(5) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(6), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 0.6)
      .pan(-0.5).play(times(6) + 0.1, 6)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.0)
      .sine(1103.37 * lowHighRate)
      .pan(0.5).play(times(6) + 0.1, 8)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(7), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(7) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(7) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(8), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(8) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(8) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(9), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.7).play(times(9) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.7).play(times(9) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(10), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.6)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(10) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowRate)
      .ring(1156.29 * lowRate)
      .pan(0.9).play(times(10) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.9).play(times(10) + 0.1, 10)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(11), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.7).play(times(11) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.7).play(times(11) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(12), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.7).play(times(12) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.7).play(times(12) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(13), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.7).play(times(13) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.7).play(times(13) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(14), 0)

    val highRate = 1156.29 / 469.726
    val highRate2 = 2766.47 / 1103.37

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.6)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.5).play(times(14) + 0.1, 6)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 2.0)
      .sine(1103.37 * lowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.5).play(times(14) + 0.1, 8)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(15), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.8).play(times(15) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.8).play(times(15) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(16), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 0.6)
      .pan(0).play(times(16) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowRate)
      .pan(0.9).play(times(16) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowLowRate)
      .pan(-0.9).play(times(16) + 0.1, 10)
  }

  def development2(): Unit = {
    client.resetClock

    val absoluteTimes = Seq(0.228, 0.503, 0.864)
    val ratio = (absoluteTimes(1) - absoluteTimes.head) / (absoluteTimes(2) - absoluteTimes.head) // 0.43238993710691825
    val invertedRatio = 1 - ratio // 0.5676100628930818
    val longTime = invertedRatio * 6
    val shortTime = ratio * 6

    println(s"Longtime $longTime shortTime $shortTime")

    val times = Melody.absolute(0, Seq(
      longTime, longTime, longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime, shortTime * ratio,
      longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime * ratio,
      longTime, longTime
    ))


    val lowRate = 469.726 / 1156.29
    val lowLowRate = lowRate * lowRate

    val highRate = 1156.29 / 469.726
    val highHighRate = highRate * highRate
    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times.head, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times.head + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times.head + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 2.0)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(1), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.6)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(1) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 3.0)
      .sine(469.726 * lowRate)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.9).play(times(1) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 3.0)
      .sine(469.726 * lowLowRate)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.9).play(times(1) + 0.1, 10)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(2), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(2) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(2) + 0.02, 4)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 2.0)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(3), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(3) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(3) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(4), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(4) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(4) + 0.02, 4)


    val lowHighRate = 1103.37 / 2766.47
    val lowLowHighRate = lowHighRate * lowHighRate
    val highRate2 = 2766.47 / 1103.37


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(5), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.8).play(times(5) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.8).play(times(5) + 0.02, 4)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(6), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.6)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.5).play(times(6) + 0.1, 6)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 2.0)
      .sine(1103.37 * lowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.5).play(times(6) + 0.1, 8)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(7), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.8).play(times(7) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.8).play(times(7) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(8), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.8).play(times(8) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.8).play(times(8) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 2.0)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(9), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(9) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(9) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(10), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.6)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(10) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.9).play(times(10) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.9).play(times(10) + 0.1, 10)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 2.0)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(11), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(11) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(11) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(12), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.8).play(times(12) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.8).play(times(12) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(13), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.8).play(times(13) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.8).play(times(13) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0).play(times(14), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.6)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(-0.5).play(times(14) + 0.1, 6)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 2.0)
      .sine(1103.37 * lowHighRate)
      .ring(1103.37 * highRate2)
      .lowPass(1103.37 * highRate)
      .pan(0.5).play(times(14) + 0.1, 8)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(15), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.8).play(times(15) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 4.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.8).play(times(15) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 2.0)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(16), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.6)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0).play(times(16) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowRate)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(0.9).play(times(16) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowLowRate)
      .ring(469.726 * highRate)
      .lowPass(1156.29 * highRate)
      .pan(-0.9).play(times(16) + 0.1, 10)
  }

  def development1(): Unit = {
    client.resetClock

    val absoluteTimes = Seq(0.228, 0.503, 0.864)
    val ratio = (absoluteTimes(1) - absoluteTimes.head) / (absoluteTimes(2) - absoluteTimes.head) // 0.43238993710691825
    val invertedRatio = 1 - ratio // 0.5676100628930818
    val longTime = invertedRatio * 8
    val shortTime = ratio * 8

    println(s"Longtime $longTime shortTime $shortTime")

    val times = Melody.absolute(0, Seq(
      longTime, longTime, longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime, shortTime * ratio,
      longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime * ratio,
      longTime, longTime
    ))

    val lowRate = 469.726 / 1156.29
    val lowLowRate = lowRate * lowRate

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times.head, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times.head + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times.head + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(1), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.6)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(1) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowRate)
      .ring(1156.29 * lowRate)
      .pan(0.9).play(times(1) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.9).play(times(1) + 0.1, 10)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(2), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(2) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(2) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(3), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(3) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(3) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(4), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(4) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(4) + 0.02, 4)


    val lowHighRate = 1103.37 / 2766.47
    val lowLowHighRate = lowHighRate * lowHighRate


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(5), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.7).play(times(5) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(-0.7).play(times(5) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(6), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.6)
      .ring(1103.37 * lowHighRate)
      .pan(-0.5).play(times(6) + 0.1, 6)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 2.0)
      .sine(1103.37 * lowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.5).play(times(6) + 0.1, 8)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(7), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.7).play(times(7) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(-0.7).play(times(7) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(8), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.7).play(times(8) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(-0.7).play(times(8) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(9), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(9) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(9) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(10), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.6)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(10) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowRate)
      .ring(1156.29 * lowRate)
      .pan(0.9).play(times(10) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.9).play(times(10) + 0.1, 10)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(11), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(11) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(11) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(12), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.7).play(times(12) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(-0.7).play(times(12) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(13), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.7).play(times(13) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 6.0)
      .lowPass(1103.37 * lowLowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(-0.7).play(times(13) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 3.0)
      .ring(1103.37 * lowHighRate)
      .pan(0).play(times(14), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.6)
      .ring(1103.37 * lowHighRate)
      .pan(-0.5).play(times(14) + 0.1, 6)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 2.0)
      .sine(1103.37 * lowHighRate)
      .ring(1103.37 * lowHighRate)
      .pan(0.5).play(times(14) + 0.1, 8)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(15), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(0.7).play(times(15) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 6.0)
      .lowPass(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.7).play(times(15) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 3.0)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(16), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.6)
      .ring(1156.29 * lowRate)
      .pan(0).play(times(16) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowRate)
      .ring(1156.29 * lowRate)
      .pan(0.9).play(times(16) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 2.0)
      .sine(469.726 * lowLowRate)
      .ring(1156.29 * lowRate)
      .pan(-0.9).play(times(16) + 0.1, 10)
  }

  def exposition(): Unit = {
    val absoluteTimes = Seq(0.228, 0.503, 0.864)
    val ratio = (absoluteTimes(1) - absoluteTimes.head) / (absoluteTimes(2) - absoluteTimes.head) // 0.43238993710691825
    val invertedRatio = 1 - ratio // 0.5676100628930818
    val longTime = invertedRatio * 10 // 5.6761006289308185
    val shortTime = ratio * 10 // 4.323899371069182

    val times = Melody.absolute(0, Seq(
      longTime, longTime, longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime, shortTime * ratio,
      longTime, longTime, longTime * invertedRatio,
      shortTime, shortTime, shortTime * ratio,
      longTime, longTime))

    println(s"Times $times")

    client.resetClock

    val lowRate = 469.726 / 1156.29
    val lowLowRate = lowRate * lowRate

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times.head, 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times.head + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times.head + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(1), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 0.6)
      .pan(0).play(times(1) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowRate)
      .pan(0.9).play(times(1) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowLowRate)
      .pan(-0.9).play(times(1) + 0.1, 10)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(2), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(2) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(2) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(3), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(3) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(3) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(4), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(4) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(4) + 0.02, 4)


    val lowHighRate = 1103.37 / 2766.47
    val lowLowHighRate = lowHighRate * lowHighRate

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(5), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(5) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(5) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(6), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 0.6)
      .pan(-0.5).play(times(6) + 0.1, 6)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.0)
      .sine(1103.37 * lowHighRate)
      .pan(0.5).play(times(6) + 0.1, 8)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(7), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(7) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(7) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(8), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(8) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(8) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(9), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(9) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(9) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(10), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 0.6)
      .pan(0).play(times(10) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowRate)
      .pan(0.9).play(times(10) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowLowRate)
      .pan(-0.9).play(times(10) + 0.1, 10)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(11), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(11) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(11) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(12), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(12) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(12) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(13), 0)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate + 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(0.6).play(times(13) + 0.01, 2)

    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate - 0.001, 2.0)
      .lowPass(1103.37 * lowLowHighRate)
      .pan(-0.6).play(times(13) + 0.02, 4)


    soundPlays.mono(SMALL_BOWL_1)
      .playMono(lowHighRate, 1.0)
      .pan(0).play(times(14), 0)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 0.6)
      .pan(-0.5).play(times(14) + 0.1, 6)

    soundPlays.mono(MORTAR_HIT_1)
      .playMono(lowHighRate, 1.0)
      .sine(1103.37 * lowHighRate)
      .pan(0.5).play(times(14) + 0.1, 8)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate, 1.0)
      .pan(0).play(times(15), 0)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate + 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(0.6).play(times(15) + 0.01, 2)

    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowRate - 0.001, 2.0)
      .lowPass(469.726 * lowLowRate)
      .pan(-0.6).play(times(15) + 0.02, 4)


    soundPlays.mono(BIG_BOWL_2)
      .playMono(lowLowRate, 1.0)
      .pan(0).play(times(16), 0)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 0.6)
      .pan(0).play(times(16) + 0.1, 6)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowRate)
      .pan(0.9).play(times(16) + 0.1, 8)

    soundPlays.mono(MORTAR_RUMBLE_1)
      .playMono(1, 1.0)
      .sine(469.726 * lowLowRate)
      .pan(-0.9).play(times(16) + 0.1, 10)
  }
}
