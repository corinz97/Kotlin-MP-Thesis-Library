package entities.interfaces

import entities.implementations.CondorcetAlgorithm
import entities.implementations.MajorityVotesAlgorithm
import entities.implementations.MajorityVotesThenHighestScoreAlgorithm
import entities.implementations.MajorityVotesThenLowestScoreAlgorithm
import entities.types.ScoreMetric

/**
 * This inteface represents the poll to be evaluated.
 */
interface Poll<S : ScoreMetric, V : Vote> {
    /**
     * Algorithm chosen to compute the final ranking.
     */
    var pollAlgorithm: PollAlgorithm<S, V>

    /**
     * Definition of competition and its members.
     */
    var competition: Competition<S>

    /**
     * Definition of votes.
     */
    var votesList: List<V>

    /**
     * Computes the final ranking.
     */
    fun computePoll(): Ranking<S>

    /**
     * DSL-function which initializes [MajorityVotesAlgorithm].
     */
    fun majorityVotesAlgorithm(
        algInit: PollAlgorithm<S, SinglePreferenceVote<S>>.() -> Unit,
    ): PollAlgorithm<S, SinglePreferenceVote<S>>

    /**
     * DSL-function which initializes [MajorityVotesThenHighestScoreAlgorithm].
     */
    fun majorityVotesHScoreAlgorithm(
        algInit: PollAlgorithm<S, SinglePreferenceVote<S>>.() -> Unit,
    ): PollAlgorithm<S, SinglePreferenceVote<S>>

    /**
     * DSL-function which initializes [MajorityVotesThenLowestScoreAlgorithm].
     */
    fun majorityVotesLScoreAlgorithm(
        algInit: PollAlgorithm<S, SinglePreferenceVote<S>>.() -> Unit,
    ): PollAlgorithm<S, SinglePreferenceVote<S>>

    /**
     * DSL-function which initializes [CondorcetAlgorithm].
     */
    fun condorcetAlgorithm(
        algInit: PollAlgorithm<S, ListOfPreferencesVote<S>>.() -> Unit,
    ): PollAlgorithm<S, ListOfPreferencesVote<S>>

    /**
     * DSL-function which initializes [SchultzeAlgorithm].
     */
    fun schultzeAlgorithm(
        algInit: PollAlgorithm<S, ListOfPreferencesVote<S>>.() -> Unit,
    ): PollAlgorithm<S, ListOfPreferencesVote<S>>

    /**
     * Shortcut which assigns the value to [pollAlgorithm].
     */
    operator fun PollAlgorithm<S, V>.unaryMinus()

    /**
     * Shortcut which assigns the value to [competition].
     */
    operator fun Competition<S>.unaryMinus()

    /**
     * Shortcut to add a vote in [votesList].
     */
    operator fun V.unaryPlus()

    /**
     * DSL-function which initializes a [Competition].
     */
    fun competition(competitionName: String, compInit: Competition<S>.() -> Unit): Competition<S>

    /**
     * Shortcut which allows competitors' name chaining in the list.
     */
    infix fun List<String>.then(s: String): List<String>

    /**
     * Shortcut which allows competitors' name chaining in the list.
     */
    infix fun String.then(s: String): List<String>

    /**
     * This function allows a  shortcut to create a [ListOfPreferencesVote],
     * given the name list of [Competitor] voted by a [Voter], distinguished by its identifier.
     */
    infix fun List<String>.votedBy(voterIdentifier: String): ListOfPreferencesVote<S>

    /**
     * This function allows a  shortcut to create a [SinglePreferenceVote],
     * given the name of [Competitor] voted by a [Voter], distinguished by its identifier.
     */
    infix fun String.votedBy(voterIdentifier: String): SinglePreferenceVote<S>

    /**
     * This function allows a  shortcut to create a [ListOfPreferencesVote],
     * given the name list of [Competitor]. [Voter] identifier is randomly generated.
     */
    fun List<String>.asAnonymousVote(): ListOfPreferencesVote<S>

    /**
     * This function allows a  shortcut to create a [SinglePreferenceVote],
     * given the name of [Competitor]. [Voter] identifier is randomly generated.
     */
    fun String.asAnonymousVote(): SinglePreferenceVote<S>
}
